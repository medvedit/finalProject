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
    with open("notes.json", "w") as file:
        json.dump(note, file)


def load_notes():
    if os.path.exists("notes.json"):
        with open("notes.json", "r") as file:
            note.extend(json.load(file))
