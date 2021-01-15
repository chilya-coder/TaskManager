package ua.edu.sumdu.j2se.chimyrys.tasks.controller;

import ua.edu.sumdu.j2se.chimyrys.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.chimyrys.tasks.model.TaskIO;
import ua.edu.sumdu.j2se.chimyrys.tasks.view.MainView;

import java.io.*;
import java.util.NoSuchElementException;

public class MainController extends Controller{

    public MainController() {
        model = new ArrayTaskList();
        File file = new File("test.json");
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            file.createNewFile();
            TaskIO.read(model, fileInputStream);
        } catch (IOException ignored) {
            System.out.println("No tasks were found");
        }
    }

    @Override
    public void action() {
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
        switch (userChoice) {
            case SHOW_ALL_TASKS:
                controller = new AllTasksController();
                break;
            case SHOW_CALENDAR:
                controller = new CalendarController();
                break;
            case ADD_UPDATE_TASK:
                break;
            case SHOW_TASK_INFO:
                controller = new ShowTaskController();

        }
        try {
            controller.action();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
