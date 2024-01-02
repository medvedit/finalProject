package sa.medwed.controllers;

import sa.medwed.model.Toy;
import sa.medwed.model.ToyOperation;
import static sa.medwed.model.Constants.FILE_TOYS_NAME;
import static sa.medwed.model.Constants.FILE_PRESENT_TOYS_NAME;
import java.util.ArrayList;


public class Controller {

    private final ToyOperation toyOperation;

    public Controller(ToyOperation toyOperation) {
        this.toyOperation = toyOperation;
    }

    // Добавить игрушку в список разыгрываемых игрушек
    public void saveToy(Toy toy) {
        toyOperation.addToy(toy);
    }

    // Чтение из файла списка всех разыгрываемых игрушек
    public ArrayList<Toy> readToyList() {
        ArrayList<Toy> toys = toyOperation.getAllToys(FILE_TOYS_NAME);
        return toys;
    }

    // Получение списка всех выданных игрушек
    public ArrayList<String> readPresentToyList() {
        ArrayList<String> toys = toyOperation.getAllPresentToys(FILE_PRESENT_TOYS_NAME);
        return toys;
    }

    // Изменить частоту выпадения (вес) игрушки
    public boolean changeToyDrop(int idToy, int dropToy) {
        return toyOperation.changeDrop(idToy, dropToy);
    }

    // Провести розыгрыш одной игрушки
    public Toy lotToy() {
        return  toyOperation.lotOneToy();
    }

    // Удалить выданную игрушку из списка разыгранных
    public void giveAwayToy(Toy toy) {
        toyOperation.giveToy(toy);
    }

    // Очистка файла выданных игрушек при окончании работы программы
    public void clearPresentFile() {
        toyOperation.clearFile();
    }
}
