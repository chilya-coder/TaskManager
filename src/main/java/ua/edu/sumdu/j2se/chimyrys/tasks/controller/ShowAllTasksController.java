package ua.edu.sumdu.j2se.chimyrys.tasks.controller;

import ua.edu.sumdu.j2se.chimyrys.tasks.StringUtils;
import ua.edu.sumdu.j2se.chimyrys.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.chimyrys.tasks.view.ShowTaskView;

public class ShowAllTasksController extends Controller {
    private ShowTaskView view;
    public ShowAllTasksController(AbstractTaskList model) {
        super();
        view = new ShowTaskView(model);
        logger.info(this.getClass() + StringUtils.CONSTRUCTOR_WORKED);
    }
    @Override
    public void action() {
        logger.info(this.getClass() + StringUtils.ACTION_WORKED);
        view.printIndexTitleTask();
    }
}
