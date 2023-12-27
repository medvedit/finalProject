from notebook_function import create_note, delete_note, edit_note, load_notes, read_notes


load_notes()

while True:
    print("Меню:")
    print("1. Создать заметку")
    print("2. Вывести в консоль все заметки")
    print("3. Редактировать заметку")
    print("4. Удалить заметку")
    print("5. Выход")
    print()

    choice = input("Введите цифру необходимого действия: ")

    if choice == "1":
        create_note()
    elif choice == "2":
        read_notes()
    elif choice == "3":
          edit_note()
    elif choice == "4":
        delete_note()
    elif choice == "5":
        break

    else:
        print("Некорректный номер команды. Попробуете снова...")