package ua.edu.sumdu.j2se.chimyrys.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.chimyrys.tasks.StringUtils;
import ua.edu.sumdu.j2se.chimyrys.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.chimyrys.tasks.view.ShowTaskView;

public class ShowAllTasksController extends Controller {
    private final static Logger logger = Logger.getLogger(ShowAllTasksController.class);
    private ShowTaskView view;
    public ShowAllTasksController(AbstractTaskList model) {
        super();
        view = new ShowTaskView(model);
    }
    @Override
    public void action() {
        logger.debug(StringUtils.ACTION_WORKED);
        view.printIndexTitleTask();
        logger.info("All tasks are printed");
    }
}
