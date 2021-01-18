package ua.edu.sumdu.j2se.chimyrys.tasks.view;

import ua.edu.sumdu.j2se.chimyrys.tasks.StringUtils;
import ua.edu.sumdu.j2se.chimyrys.tasks.controller.ActionChoice;
import ua.edu.sumdu.j2se.chimyrys.tasks.controller.TaskAction;
import ua.edu.sumdu.j2se.chimyrys.tasks.model.AbstractTaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ModifyTasksView {
    private Scanner scanner;
    public ModifyTasksView() {
        scanner = new Scanner(System.in);
    }
    /**
     * Method creates new TaskAction object with chosen action and index of task
     * provided by user
     * @param model AbstractTaskList
     * @return TaskAction
     */
    public TaskAction getTaskActionFromUser(AbstractTaskList model) {
        printMenu();
        ActionChoice actionChoice = getTaskAction();
        if (actionChoice.equals(ActionChoice.BACK)) {
            return new TaskAction(actionChoice);
        }
        if (actionChoice.equals(ActionChoice.ADD)) {
            return new TaskAction(actionChoice);
        }
        while ((model.size() == 0) && (actionChoice.equals(ActionChoice.UPDATE)
                || actionChoice.equals(ActionChoice.DELETE))) {
            System.out.println("You need to add tasks to the list first!");
            actionChoice = getTaskAction();
        }
        new ShowTaskView(model).printIndexTitleTask();
        int index = getTaskIndex(model) - 1;
        return new TaskAction(actionChoice, index);
    }
    /**
     * Method that prints menu of actions
     */
    private void printMenu() {
        System.out.println("What would you like to do?");
        System.out.println("1 - add new task");
        System.out.println("2 - update existing task");
        System.out.println("3 - delete task");
        System.out.println("4 - back");
    }
    /**
     * Method that gets user's choice as int and checks if
     * there is a such value in ActionChoice enum
     * @return ActionChoice
     */
    private ActionChoice getTaskAction() {
        System.out.print("Enter your choice: ");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        return Arrays.stream(ActionChoice.values())
                .filter(choice -> choice.getId() == id)
                .iterator().next();
    }
    public List<LocalDateTime> addTimeToTaskView() {
        List<LocalDateTime> localDateTimes = new ArrayList<>();
        System.out.println("You're adding new task to list: ");
        if (getIsRepeated()) {
            System.out.println("Enter start time for your task:");
            localDateTimes.add(getTaskTime());
            System.out.println("Enter end time for your task:");
            localDateTimes.add(getTaskTime());
        } else {
            System.out.println("Enter time of your task:");
            localDateTimes.add(getTaskTime());
        }
        return localDateTimes;
    }
    /**
     * Method that gets index of task provided by User
     * and checks if this index is valid
     * @param model AbtsractTaskList
     * @return int
     */
    public int getTaskIndex(AbstractTaskList model) {
        int id = 0;
        while (true) {
            System.out.print("Enter number of your chosen task: #");
            try {
                id = scanner.nextInt();
            } catch (InputMismatchException е) {
                System.out.println("You entered invalid index");
            }
            if (id <= 0 || id > model.size() + 1) {
                System.out.println("That number doesn't exist! Try another");
            }
            else {
                return id;
            }
        }
    }

    public String getTaskTitle() {
        System.out.println("Enter title of your task: ");
        String title;
        try {
            title = scanner.nextLine();
        } catch (NoSuchElementException e) {
            System.out.println("You entered invalid value. "
                    + "Enter true or false");
            title = getTaskTitle();
        }
        return scanner.nextLine();

    }
    public boolean getTaskIsActive() {
        System.out.println("Enter if task is active (true or false): ");
        boolean isActive;
        try {
            isActive = scanner.nextBoolean();
        } catch (NoSuchElementException e) {
            System.out.println("You entered invalid value. "
                    + "Enter true or false");
            isActive = getTaskIsActive();
        }
        return isActive;
    }
    public boolean getIsRepeated() {
        System.out.println("Enter if task is repeated (true or false): ");
        boolean isRepeated;
        try {
            isRepeated = scanner.nextBoolean();
        } catch (NoSuchElementException e) {
            System.out.println("You entered invalid value. "
                    + "Enter true or false");
            isRepeated = getTaskIsActive();
        }
        return isRepeated;
    }
    public int getInterval() {
        System.out.println("Enter interval of task as integer value: ");
        int interval;
        try {
            interval = scanner.nextInt();
        } catch (NoSuchElementException e) {
            System.out.println("You entered invalid value. Enter integer");
            interval = getInterval();
        }
        return interval;
    }
    public LocalDateTime getTaskTime() {
        LocalDateTime time = null;
        System.out.println(StringUtils.INVALID_DATA_INPUT
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        try {
                System.out.println("Enter year, for e.g. 2021: ");
                int year = scanner.nextInt();
                System.out.println("Enter month, for e.g. 01: ");
                int month = scanner.nextInt();
                System.out.println("Enter day, for e.g. 12: ");
                int day = scanner.nextInt();
                System.out.println("Enter hour, for e.g. 13: ");
                int hour = scanner.nextInt();
                System.out.println("Enter minute, for e.g. 05: ");
                int minute = scanner.nextInt();
                System.out.println("Enter second, for e.g. 00: ");
                int second = scanner.nextInt();
                time = LocalDateTime.of(year, month, day, hour, minute, second);
        } catch (NoSuchElementException e) {
            System.out.println("You entered invalid values. "
                    + "Enter integer numbers only");
        }
        return time;
    }
}
