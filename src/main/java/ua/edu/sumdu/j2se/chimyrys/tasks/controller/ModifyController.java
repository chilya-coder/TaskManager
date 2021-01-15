package ua.edu.sumdu.j2se.chimyrys.tasks.controller;

import ua.edu.sumdu.j2se.chimyrys.tasks.view.ModifyTasksView;

public class ModifyController extends Controller {
    private ModifyTasksView view;

    @Override
    public void action() {
        view.getTaskActionFromUser(model);

    }

    public void doAction(TaskAction action) {
        action.getAction();
    }
}
