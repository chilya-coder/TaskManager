package ua.edu.sumdu.j2se.chimyrys.tasks.view;

import ua.edu.sumdu.j2se.chimyrys.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.chimyrys.tasks.model.Task;

public class NotifyView {
    public void printNotify(AbstractTaskList tasks) {
        for (Task task: tasks) {
            System.out.print("\n" + task.getTitle() + " start at " + tasks.getTask(0).getTime());
        }
        System.out.print("\n");
    }
}
