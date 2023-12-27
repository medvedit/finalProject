from datetime import datetime




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

    print("Заметка успешно создана!")