package sa.medwed.views;


import java.util.ArrayList;
import java.util.Scanner;

import sa.medwed.controllers.Controller;
import sa.medwed.model.Toy;



public class ViewUser {

    private Controller controller;

    // Список разыгранных игрушек
    private ArrayList<Toy> presentToy = new ArrayList<>();

    public ViewUser(Controller controller) {
        this.controller = controller;
    }

    public int userMenu() {
        System.out.println("Введите номер команды:");
        System.out.println("1 - добавить игрушку в список разыгрываемых игрушек");
        System.out.println("2 - вывести список всех разыгрываемых игрушек");
        System.out.println("3 - изменить частоту выпадения (вес) игрушки");
        System.out.println("4 - провести розыгрыш одной игрушки (случайный выбор из всего списка игрушек)");
        System.out.println("5 - провести серию розыгрышей (случайный выбор из всего списка игрушек)");
        System.out.println("6 - вывести список всех разыгранных и не выданных игрушек");
        System.out.println("7 - выдать призовую игрушку из списка разыгранных");
        System.out.println("8 - вывести список всех выданных игрушек");
        System.out.println("9 - выход");
        try {
            int choise = Integer.parseInt(userInput("Ваш выбор: "));
            return choise;
        } catch (Exception e) {
            System.out.println("""

                    Введено некорректное значение! Ожидался ввод значения целого числа.\s
                    Программа прекращает свою работу!
                    
                    """);
        }
        return 9;
    }

    public void run() {
        while (true) {
            int command = userMenu();

            if (command <= 0 || command > 9) {
                System.out.println("""

                        Такой команды нет! Ожидаю ввод корректного номера команды.\s
                        Попробуйте еще раз!

                        """);
            }

            if (command == 1) { // Добавить игрушку в список разыгрываемых игрушек
                System.out.println("Добавляем новую игрушку:");
                String name = userInput("Название: ");
                int count = 0;
                try {
                    count = Integer.parseInt(userInput("Количество: "));
                } catch (Exception e) {
                    System.out.println("""

                            Введено некорректное значение! Ожидаю ввод целого числа.
                            Добавление игрушки не заверено!
                            
                            """);
                    run();
                }
                int drop = 0;
                try {
                    drop = Integer.parseInt(userInput("Частота выпадения (вес в % от 1 до 100): "));
                    if (drop < 1 || drop > 100) {
                        System.out.println("""
                                                                
                                    Введено некорректное значение!
                                    Ожидаемый диапазон значений ввода целого числа от 1 до 100.
                                                                
                                    """);
                        run();
                    }
                } catch (Exception e) {
                    System.out.println("""

                            Введено некорректное значение! Ожидаю ввод целого числа.
                            Добавление игрушки не заверено!
                            
                            """);
                    run();
                }
                if (drop < 0 || drop > 100) {
                    System.out.println("""
                            
                            Частота выпадения (веса) должна быть в диапазоне от 1% до 100%!
                            Добавление игрушки не заверено!
                            
                            """);
                    run();
                }
                controller.saveToy(new Toy(name, count, drop));
                System.out.println("Игрушка добавлена!");
            }

            if (command == 2) { // Вывести список всех разыгрываемых игрушек
                ArrayList<Toy> toys = controller.readToyList();
                if (toys.size() > 0) {
                    System.out.println("\nНа данный момент для розыгрыша доступны следующие игрушки:");
                    toys.forEach(System.out::println);
                    System.out.println();
                } else {
                    System.out.println("""
                            
                            Файл с разыгрываемыми игрушками пустой!
                            
                            """);
                }
            }

            if (command == 3) { // Изменить частоту выпадения (вес) игрушки
                int idToy = 0;
                try {
                    idToy = Integer.parseInt(userInput("Введите id игрушки: "));
                } catch (Exception e) {
                    System.out.println("""
                            
                            Введено некорректное значение! Ожидаю ввод целого числа.
                            
                            """);
                    run();
                }
                int dropToy = 0;
                try {
                    dropToy = Integer.parseInt(userInput(
                            "Введите новую частоту выпадения (веса) игрушки, в % (от 1 до 100): "));
                        if (dropToy < 1 || dropToy > 100) {
                            System.out.println("""
                                                                
                                    Введено некорректное значение!
                                    Ожидаемый диапазон значений ввода целого числа от 1 до 100.
                                                                
                                    """);
                            run();
                        }
                } catch (Exception e) {
                    System.out.println("""
                            
                            Введено некорректное значение! Ожидаю ввод целого числа.
                            
                            """);
                    run();
                }
                boolean flag = controller.changeToyDrop(idToy, dropToy);
                if (!flag) {
                    System.out.println("\nНет игрушки с таким id!\n");
                } else {
                    System.out.println("\nЧастота выпадения (вес) игрушки обновлена!\n");
                }
            }

            if (command == 4) { // Провести розыгрыш одной игрушки
                Toy lot = controller.lotToy();
                presentToy.add(lot);
                System.out.println("\nРозыгрыш проведен! Разыграна игрушка:");
                System.out.println("ID = " + lot.getId() + ", название игрушки = '" + lot.getName() + "'" + "\n");
            }

            if (command == 5) { // Провести серию розыгрышей
                int countDraw = 0;
                try {
                    countDraw = Integer.parseInt(userInput("Введите количество " +
                            "желаемых розыгрышей (целое число): "));
                } catch (Exception e) {
                    System.out.println("""
                            
                            Введено некорректное значение! Ожидаю ввод целого числа.
                            
                            """);
                    run();
                }
                System.out.println("\nСерия из " + countDraw + " розыгрышей проведена! Разыграны игрушки:");
                for (int i = 1; i <= countDraw; i++) {
                    Toy lot = controller.lotToy();
                    presentToy.add(lot);
                    System.out.println(i + ". '" + lot.getName() + "'");
                }
                System.out.println();
            }

            if (command == 6) { // Вывести список всех разыгранных и не выданных игрушек
                if (presentToy.size() == 0) {
                    System.out.println("\nПока еще ни одна игрушка не участвовала в розыгрыше!\n");
                } else {
                    System.out.println("\nРазыгранные и не выданные игрушки:");
                    for (int i = 0; i < presentToy.size(); i++) {
                        System.out.println(i + 1 + ". '" + presentToy.get(i).getName() + "'");
                    }
                    System.out.println();
                }
            }

            if (command == 7) { // Выдать призовую игрушку
                if (presentToy.size() == 0) {
                    System.out.println("\nСписок разыгранных и не выданных игрушек пуст!\n");
                } else {
                    System.out.println("\nВыдана призовая игрушка:");
                    System.out.println(presentToy.get(0).getName() + "\n");
                    controller.giveAwayToy(presentToy.get(0));
                    presentToy.remove(0);
                }
            }

            if (command == 8) { // Вывести список всех выданных игрушек
                ArrayList<String> toys = controller.readPresentToyList();
                if (toys.size() > 0) {
                    System.out.println("Были выданы следующие игрушки:");
                    toys.forEach(System.out::println);
                } else {
                    System.out.println("Файл с выданными игрушками пустой!");
                }
            }

            if (command == 9) {
                System.out.println("До свидания!");
                controller.clearPresentFile();
                return;
            }
        }
    }

    private String userInput(String message) {
        Scanner scan = new Scanner(System.in);
        System.out.print(message);
        return scan.nextLine();
    }

}
