package ua.edu.sumdu.j2se.chimyrys.tasks.controller;

import ua.edu.sumdu.j2se.chimyrys.tasks.model.TaskIO;
import ua.edu.sumdu.j2se.chimyrys.tasks.view.MainView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;

public class MainController extends Controller {
    public MainController() {
        File file = new File("test.json");
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            file.createNewFile();
            TaskIO.read(model, fileInputStream);
        } catch (IOException ignored) {
            System.out.println("No tasks were found");
        }
    }

    /**
     * Method that calls specified controller via
     * user's choice provided in MainView.
     * Throws NoSuchElementException if user's choice was invalid.
     * Throws Exception if Controller object stays null
     */
    @Override
    public void action() {
        //model = new ArrayTaskList();
        MainView view = new MainView();
        view.printMainMenu();
        UserChoice userChoice = null;
        try {
             userChoice = view.getUserChoice();
        } catch (NoSuchElementException e) {
            System.out.println("Invalid value was put");
            action();
            return;
        }
        Controller controller = null;
        boolean go = true;
        switch (userChoice) {
            case SHOW_ALL_TASKS:
                controller = new AllTasksController();
                break;
            case SHOW_CALENDAR:
                controller = new CalendarController();
                break;
            case ADD_UPDATE_DELETE_TASK:
                controller = new ModifyController();
                break;
            case SHOW_TASK_INFO:
                controller = new ShowTaskController();
                break;
            case QUIT:
                System.out.println("See you later!");
                System.exit(0);
                break;
        }
        try {
            controller.action();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
