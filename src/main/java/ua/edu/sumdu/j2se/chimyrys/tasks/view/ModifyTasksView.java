package ua.edu.sumdu.j2se.chimyrys.tasks.view;

import ua.edu.sumdu.j2se.chimyrys.tasks.controller.ActionChoice;
import ua.edu.sumdu.j2se.chimyrys.tasks.controller.TaskAction;
import ua.edu.sumdu.j2se.chimyrys.tasks.model.AbstractTaskList;

import java.util.Arrays;
import java.util.Scanner;

public class ModifyTasksView {

    public TaskAction getTaskActionFromUser(AbstractTaskList model) {
        printMenu();
        ActionChoice actionChoice = getActionFromUser();
        model.printIndexTitleTask();


        return new TaskAction();
    }
    private void printMenu() {
        System.out.println("What would you like to do?");
        System.out.println("1 - add new task");
        System.out.println("2 - update existing task");
        System.out.println("3 - delete task");
    }
    private ActionChoice getActionFromUser() {
        System.out.print("Enter your choice: ");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        return Arrays.stream(ActionChoice.values())
                .filter(choice -> choice.getId() == id)
                .iterator().next();
    }
    private int getTaskId() {
        return 1;
    }
}
