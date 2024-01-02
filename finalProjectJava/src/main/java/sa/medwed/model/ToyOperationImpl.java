package sa.medwed.model;


import java.util.ArrayList;
import java.util.Random;

import static sa.medwed.model.Constants.FILE_TOYS_NAME;
import static sa.medwed.model.Constants.FILE_PRESENT_TOYS_NAME;

public class ToyOperationImpl implements ToyOperation{

    private final FileOperation fileOperation;
    private final ToyConverter toyConverter = new ToyConverter();

    public ToyOperationImpl(FileOperation fileOperation) {
        this.fileOperation = fileOperation;
    }

    // Получение списка всех готовых к розыгрышу игрушек
    @Override
    public ArrayList<Toy> getAllToys(String fileName) {
        ArrayList<String> toyList = fileOperation.readAllToys(fileName);
        ArrayList<Toy> toys = new ArrayList<>();
        for (String line : toyList) {
            toys.add(toyConverter.convert(line));
        }
        return toys;
    }

    // Добавление новой игрушки
    @Override
    public int addToy(Toy toy) {
        ArrayList<Toy> toys = getAllToys(FILE_TOYS_NAME);
        int max = 0;
        for (Toy item : toys) {
            int id = item.getId();
            if (max < id) {
                max = id;
            }
        }
        int newId = max + 1;    // Определили id для новой игрушки
        toy.setId(newId);
        toys.add(toy);
        writeToys(FILE_TOYS_NAME, toys);
        return newId;
    }

    // Изменение частоты выпадения игрушки (вес в % от 100)
    @Override
    public boolean changeDrop(int idToy, int dropToy) {
        ArrayList<Toy> toys = getAllToys(FILE_TOYS_NAME);
        boolean flag = false;
        for (Toy item : toys) {
            int currentId = item.getId();
            if (currentId == idToy) {
                item.setDrop(dropToy);
                flag = true;
            }
        }
        writeToys(FILE_TOYS_NAME,toys);
        return flag;
    }

    // Розыгрыш одной игрушки
    @Override
    public Toy lotOneToy() {
        ArrayList<Toy> toys = getAllToys(FILE_TOYS_NAME);
        boolean flag = false;
        while (!flag) {
            int currentDrop = new Random().nextInt(0, 101);
            ArrayList<Toy> lotToys = new ArrayList<>();
            for (Toy item : toys) {
                if (currentDrop == item.getDrop()) {
                    lotToys.add(item);
                }
            }
            int sizeLot = lotToys.size();
            if (sizeLot > 0) {
                if (sizeLot == 1) {
                    ArrayList<Toy> newArrayToys = toysAfterDraw(toys, lotToys.get(0));
                    writeToys(FILE_TOYS_NAME, newArrayToys);
                    return lotToys.get(0);
                } else {
                    int currentLot = new Random().nextInt(1, sizeLot);
                    ArrayList<Toy> newArrayToys = toysAfterDraw(toys, lotToys.get(currentLot));
                    writeToys(FILE_TOYS_NAME, newArrayToys);
                    return lotToys.get(currentLot);
                }
            }
        }
        return null;
    }

    // Уменьшение количества игрушек после розыгрыша
    @Override
    public ArrayList<Toy> toysAfterDraw(ArrayList<Toy> toys, Toy lotToy) {
        ArrayList<Toy> newToys = new ArrayList<>();
        for (Toy item : toys) {
            if (item.getId() == lotToy.getId()) {
                item.setCount(item.getCount() - 1);
            }
            if (item.getCount() > 0) {
                newToys.add(item);
            }
        }
        return newToys;
    }

    // Выдача призовой игрушки
    @Override
    public void giveToy(Toy toy) {
        writePresentToy(FILE_PRESENT_TOYS_NAME, toy);
    }

    // Запись всех игрушек в файл
    @Override
    public void writeToys(String fileName, ArrayList<Toy> toys) {
        ArrayList<String> toysList = new ArrayList<>();
        for (Toy item : toys) {
            toysList.add(toyConverter.convert(item));
        }
        fileOperation.saveAllToys(fileName, toysList);
    }

    // Запись выданной игрушки в файл
    @Override
    public void writePresentToy(String fileName, Toy toy) {
        fileOperation.savePresentToy(fileName, toy.getName());
    }

    // Очистка файла выданных игрушек после окончания работы программы
    @Override
    public void clearFile() {
        fileOperation.clearPresentToysFile(FILE_PRESENT_TOYS_NAME);
    }

    // Получение списка всех выданных игрушек
    @Override
    public ArrayList<String> getAllPresentToys(String fileName) {
        ArrayList<String> toyList = fileOperation.readAllPresentToys(fileName);
        ArrayList<String> toys = new ArrayList<>();
        for (String line : toyList) {
            toys.add(line);
        }
        return toys;
    }

}
