package ua.edu.sumdu.j2se.chimyrys.tasks.view;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.chimyrys.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.chimyrys.tasks.model.Task;

import java.time.format.DateTimeFormatter;

public class ShowTaskView {
    private final static Logger logger = Logger.getLogger(ShowTaskView.class);
    private AbstractTaskList model;
    public ShowTaskView(AbstractTaskList model) {
        super();
        this.model = model;
    }
    public void printIndexTitleTask() {
        logger.debug("printIndexTitleTask has worked");
        if (model.size() == 0) {
            System.out.println("List of tasks is empty!");
            return;
        }
        for (int i = 0; i < model.size(); ++i) {
            System.out.println("#" + (i + 1) + " "
                    + model.getTask(i).getTitle());
        }
        logger.debug("Tasks' titles with indexes were printed to User");
    }
    public void printTaskByIndex(int index) {
        logger.debug("has worked");
        System.out.println("Detailed info of task #" + index + ":");
        printDetailedTaskInfo(model.getTask(index - 1));
        logger.debug("Task was printed by user's choice " + index);
    }
    public static void printDetailedTaskInfo(Task task) {
        logger.debug("printDetailedTaskInfo method has worked");
        System.out.println("Task title: " + task.getTitle());
        System.out.println("Active: " + task.isActive());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (task.isRepeated()) {
            System.out.println("Start time: "
                    + task.getStartTime().format(formatter));
            System.out.println("End time: "
                    + task.getEndTime().format(formatter));
            System.out.println("Interval: "
                    + task.getRepeatInterval());
        } else {
            System.out.println("Time: " + task.getTime().format(formatter));
        }
        logger.debug("Detailed info about task "
                + task.getTitle()
                + " was printed");
    }
}
