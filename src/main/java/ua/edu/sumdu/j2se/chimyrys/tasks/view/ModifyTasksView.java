package ua.edu.sumdu.j2se.chimyrys.tasks.view;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.chimyrys.tasks.StringUtils;
import ua.edu.sumdu.j2se.chimyrys.tasks.controller.ActionChoice;
import ua.edu.sumdu.j2se.chimyrys.tasks.controller.TaskAction;
import ua.edu.sumdu.j2se.chimyrys.tasks.model.AbstractTaskList;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ModifyTasksView {
    private final static Logger logger = Logger.getLogger(ModifyTasksView.class);
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
        logger.debug("getTaskActionFromUser has worked");
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
        logger.info("User choose " + actionChoice + " " + index + "'th task");
        return new TaskAction(actionChoice, index);
    }
    /**
     * Method that prints menu of actions
     */
    private void printMenu() {
        logger.debug("Modifying tasks menu was printed");
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
        logger.debug("getTaskAction has worked");
        System.out.print("Enter your choice: ");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        return Arrays.stream(ActionChoice.values())
                .filter(choice -> choice.getId() == id)
                .iterator().next();
    }
    public List<LocalDateTime> addTimeToTaskView() {
        logger.debug("addTimeToTaskView method has worked");
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
        logger.debug("List of start and end LocalDateTime was created");
        return localDateTimes;
    }
    /**
     * Method that gets index of task provided by User
     * and checks if this index is valid
     * @param model AbtsractTaskList
     * @return int
     */
    public int getTaskIndex(AbstractTaskList model) {
        logger.debug("getTaskIndex method has worked");
        int id = 0;
        while (true) {
            System.out.print("Enter number of your chosen task: #");
            try {
                id = scanner.nextInt();
                logger.info("User entered index #" + id);
            } catch (InputMismatchException е) {
                logger.error(StringUtils.INVALID_INPUT_VALUE);
                System.out.println(StringUtils.INVALID_INPUT_VALUE);
            }
            if (id <= 0 || id > model.size() + 1) {
                System.out.println("That number doesn't exist! Try another");
            } else {
                return id;
            }
        }
    }

    public String getTaskTitle() {
        logger.debug("getTaskTitle method has worked");
        System.out.println("Enter title of your task: ");
        String title;
        try {
            title = scanner.nextLine();
            logger.info("User entered title: " + title);
        } catch (NoSuchElementException e) {
            logger.error(StringUtils.INVALID_INPUT_VALUE);
            System.out.println(StringUtils.INVALID_INPUT_VALUE);
            title = getTaskTitle();
        }
        return title;

    }
    public boolean getTaskIsActive() {
        logger.debug("getTaskIsActive method has worked");
        System.out.println("Enter if task is active (true or false): ");
        boolean isActive;
        try {
            isActive = scanner.nextBoolean();
            logger.info("User entered following active status: " + isActive);
        } catch (NoSuchElementException e) {
            logger.error(StringUtils.INVALID_INPUT_VALUE);
            System.out.println(StringUtils.INVALID_INPUT_VALUE);
            scanner.nextLine();
            isActive = getTaskIsActive();
        }
        return isActive;
    }
    public boolean getIsRepeated() {
        logger.debug("getIsRepeated method has worked");
        System.out.println("Enter if task is repeated (true or false): ");
        boolean isRepeated;
        try {
            isRepeated = scanner.nextBoolean();
            logger.info("User entered following isRepeated status: " + isRepeated);
        } catch (NoSuchElementException e) {
            logger.error(StringUtils.INVALID_INPUT_VALUE);
            System.out.println(StringUtils.INVALID_INPUT_VALUE);
            scanner.nextLine();
            isRepeated = getIsRepeated();
        }
        return isRepeated;
    }
    public int getInterval() {
        logger.debug("getInterval method has worked");
        System.out.println("Enter interval of task as integer value: ");
        int interval;
        try {
            interval = scanner.nextInt();
            logger.info("User entered following interval: " + interval);
        } catch (NoSuchElementException e) {
            logger.error(StringUtils.INVALID_INPUT_VALUE);
            System.out.println(StringUtils.INVALID_INPUT_VALUE);
            interval = getInterval();
        }
        return interval;
    }
    public LocalDateTime getTaskTime() {
        logger.debug("getTaskTime method has worked");
        LocalDateTime time = null;
        System.out.println(StringUtils.INVALID_DATE_INPUT
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        try {
            System.out.println("Enter year, for e.g. 2021: ");
            int year = getInt();
            System.out.println("Enter month, for e.g. 01: ");
            int month = getInt();
            System.out.println("Enter day, for e.g. 12: ");
            int day = getInt();
            System.out.println("Enter hour, for e.g. 13: ");
            int hour = getInt();
            System.out.println("Enter minute, for e.g. 05: ");
            int minute = getInt();
            System.out.println("Enter second, for e.g. 00: ");
            int second = getInt();
            time = LocalDateTime.of(year, month, day, hour, minute, second);
            logger.info("User entered following date: " + time);
        } catch (DateTimeException e) {
            logger.error(StringUtils.INVALID_INPUT_VALUE);
            System.out.println(StringUtils.INVALID_INPUT_VALUE);
            time = getTaskTime();
        }
        return time;
    }
    private int getInt() {
        logger.debug("getInt method has worked");
        try {
            return scanner.nextInt();
        } catch (NoSuchElementException e) {
            logger.error(StringUtils.INVALID_INPUT_VALUE);
            System.out.println(StringUtils.INVALID_INPUT_VALUE);
            scanner.nextLine();
            return getInt();
        }
    }
}
