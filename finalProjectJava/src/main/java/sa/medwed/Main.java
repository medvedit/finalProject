package sa.medwed;


import sa.medwed.controllers.Controller;
import sa.medwed.model.*;
import sa.medwed.views.ViewUser;

public class Main {

    public static void main(String[] args) {
        FileOperation fileOperation = new FileOperationImpl();
        ToyOperation toyOperation = new ToyOperationImpl(fileOperation);
        Controller controller = new Controller(toyOperation);
        ViewUser view = new ViewUser(controller);
        view.run();
    }

}
