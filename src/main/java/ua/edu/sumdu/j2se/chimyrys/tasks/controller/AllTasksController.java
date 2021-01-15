package ua.edu.sumdu.j2se.chimyrys.tasks.controller;

import ua.edu.sumdu.j2se.chimyrys.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.chimyrys.tasks.view.TasksView;

public class AllTasksController extends Controller {
    private TasksView view;
    @Override
    public void action() {

        System.out.println("All tasks: ");
    }

}
