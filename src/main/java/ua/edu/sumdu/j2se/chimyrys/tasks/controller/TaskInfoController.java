package ua.edu.sumdu.j2se.chimyrys.tasks.controller;

import ua.edu.sumdu.j2se.chimyrys.tasks.StringUtils;
import ua.edu.sumdu.j2se.chimyrys.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.chimyrys.tasks.view.ModifyTasksView;
import ua.edu.sumdu.j2se.chimyrys.tasks.view.ShowTaskView;

public class TaskInfoController extends Controller {
    private ShowTaskView view;
    private ModifyTasksView indexView;
    public TaskInfoController(AbstractTaskList model) {
        super();
        this.model = model;
        view = new ShowTaskView(model);
        indexView = new ModifyTasksView();
        logger.info(this.getClass() + StringUtils.CONSTRUCTOR_WORKED);
    }
    @Override
    public void action() {
        logger.info(this.getClass() + StringUtils.ACTION_WORKED);
        view.printIndexTitleTask();
        if (model.size() != 0) {
            view.printTaskByIndex(indexView.getTaskIndex(model));
        }
    }

}
