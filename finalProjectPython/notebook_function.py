from datetime import datetime
import json
import os




note = []


def create_note():
    note_id = len(note) + 1
    note_title = input("Введите заголовок заметки: ")
    note_body = input("Введите текст заметки: ")
    timestamp = datetime.now().strftime('%d-%m-%Y %H:%M:%S')

    notes = {
        "id": note_id,
        "title": note_title,
        "body": note_body,
        "timestamp": timestamp
    }
    note.append(notes)
    save_notes()

    print("Заметка успешно создана!")


def save_notes():
    with open("finalProject/finalProjectPython/notes.json", "w") as file:
        json.dump(note, file)


def load_notes():
    if os.path.exists("finalProject/finalProjectPython/notes.json"):
        with open("notes.json", "r") as file:
            note.extend(json.load(file))

def read_notes():
    if not note:
        print("Список заметок пуст. Начните с создания заметки.")
    else:
      for notes in note:
          print(f"ID: {notes['id']}\n "
                f"Заголовок: {notes['title']}\n "
                f"Текст: {notes['body']}\n "
                f"Дата/Время: {notes['timestamp']}\n")
          

def edit_note():
    note_id = int(input("Введите ID заметки, которую нужно отредактировать: "))
    note_index = -1

    for index, notes in enumerate(note):
        if notes['id'] == note_id:
            note_index = index
            break

    if note_index != -1:
        note_title = input("Введите новый заголовок заметки: ")
        note_body = input("Введите новый текст заметки: ")

        note[note_index]['title'] = note_title
        note[note_index]['body'] = note_body
        note[note_index]['timestamp'] = datetime.now().strftime('%Y-%m-%d %H:%M:%S')

        save_notes()
        print("Заметка успешно создана!")
    else:
        print("Заметка с указанным ID не найдена.")


def delete_note():
    note_id = int(input("Введите ID заметки, которую необходимо удалить: "))
    note_index = -1

    for index, notes in enumerate(note):
        if notes['id'] == note_id:
            note_index = index
            break
        
    if note_index != -1:
        del note[note_index]
        save_notes()
        print("Заметка успешно удалена!")
    else:
        print("Заметка с указанным ID не найдена.")
