package ua.edu.sumdu.j2se.chimyrys.tasks.controller;

import ua.edu.sumdu.j2se.chimyrys.tasks.view.MainView;

import java.util.NoSuchElementException;

public class MainController extends Controller {

    private MainView view;

    public MainController() {
        model = DataReadWriteController.readData();
        view = new MainView();
        action();
    }
    /**
     * Method that calls specified controller via
     * user's choice provided in MainView.
     * Throws NoSuchElementException if user's choice was invalid.
     * Throws Exception if Controller object stays null
     */
    @Override
    public void action() {
        do {
            view.printMainMenu();
            UserChoice userChoice;
            try {
                userChoice = view.getUserChoice();
            } catch (NoSuchElementException e) {
                System.out.println("Invalid value was put");
                action();
                return;
            }
            Controller controller = this;
            switch (userChoice) {
                case SHOW_ALL_TASKS:
                    controller = new ShowAllTasksController(model);
                    break;
                case SHOW_CALENDAR:
                    controller = new CalendarController(model);
                    break;
                case ADD_UPDATE_DELETE_TASK:
                    controller = new ModifyController(model);
                    break;
                case SHOW_TASK_INFO:
                    controller = new TaskInfoController(model);
                    break;
                case QUIT:
                    System.out.println("See you later!");
                    System.exit(0);
                    break;
            }
            controller.action();
            DataReadWriteController.writeData(model);
        } while (true);
    }
}
