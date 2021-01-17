package ua.edu.sumdu.j2se.chimyrys.tasks.controller;

import ua.edu.sumdu.j2se.chimyrys.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.chimyrys.tasks.view.ShowTaskView;

public class ShowAllTasksController extends Controller {
    private ShowTaskView view;
    public ShowAllTasksController(AbstractTaskList model) {
        super();
        view = new ShowTaskView(model);
    }
    @Override
    public void action() {
        view.printIndexTitleTask();
    }
}
