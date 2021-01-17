package ua.edu.sumdu.j2se.chimyrys.tasks.view;

import ua.edu.sumdu.j2se.chimyrys.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.chimyrys.tasks.model.Task;

import java.time.format.DateTimeFormatter;

public class ShowTaskView {
    private AbstractTaskList model;
    public ShowTaskView(AbstractTaskList model) {
        super();
        this.model = model;
    }
    public void printIndexTitleTask() {
        if (model.size() == 0) {
            System.out.println("List of tasks is empty!");
            return;
        }
        for (int i = 0; i < model.size(); ++i) {
            System.out.println("#" + (i + 1) + " "
                    + model.getTask(i).getTitle());
        }
    }
    public void printTaskByIndex(int index) {
        System.out.println("Detailed info of task #" + index + ":");
        printDetailedTaskInfo(model.getTask(index - 1));
    }
    public static void printDetailedTaskInfo(Task task) {
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
    }
}
