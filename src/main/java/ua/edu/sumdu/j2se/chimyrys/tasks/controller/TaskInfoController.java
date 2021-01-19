package ua.edu.sumdu.j2se.chimyrys.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.chimyrys.tasks.StringUtils;
import ua.edu.sumdu.j2se.chimyrys.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.chimyrys.tasks.view.ModifyTasksView;
import ua.edu.sumdu.j2se.chimyrys.tasks.view.ShowTaskView;

public class TaskInfoController extends Controller {
    private final static Logger logger = Logger.getLogger(TaskInfoController.class);
    private ShowTaskView view;
    private ModifyTasksView indexView;
    public TaskInfoController(AbstractTaskList model) {
        super();
        this.model = model;
        view = new ShowTaskView(model);
        indexView = new ModifyTasksView();
    }
    @Override
    public void action() {
        logger.debug(StringUtils.ACTION_WORKED);
        view.printIndexTitleTask();
        if (model.size() != 0) {
            view.printTaskByIndex(indexView.getTaskIndex(model));
        }
        logger.info("Detailed information about task is printed");
    }

}
