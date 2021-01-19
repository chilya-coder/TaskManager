package ua.edu.sumdu.j2se.chimyrys.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.chimyrys.tasks.StringUtils;
import ua.edu.sumdu.j2se.chimyrys.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.chimyrys.tasks.model.Task;
import ua.edu.sumdu.j2se.chimyrys.tasks.view.ModifyTasksView;
import ua.edu.sumdu.j2se.chimyrys.tasks.view.ShowTaskView;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class ModifyController extends Controller {
    private final static Logger logger = Logger.getLogger(ModifyController.class);
    private ModifyTasksView view;
    public ModifyController(AbstractTaskList model) {
        super(model);
        view = new ModifyTasksView();
    }

    @Override
    public void action() {
        logger.debug(StringUtils.ACTION_WORKED);
        TaskAction taskAction;
        try {
            taskAction = view.getTaskActionFromUser(model);
        } catch (NoSuchElementException e) {
            logger.error(StringUtils.FILE_NOT_FOUND);
            System.out.println(StringUtils.INVALID_INPUT_VALUE);
            action();
            return;
        }
        switch (taskAction.getAction()) {
            case ADD:
                logger.info("User chose to add task");
                addTask();
                break;
            case UPDATE:
                logger.info("User chose to update task");
                updateTask(taskAction.getTaskIndex());
                break;
            case DELETE:
                logger.info("User chose to delete task");
                deleteTask(taskAction.getTaskIndex());
                break;
            case BACK:
                logger.info("User chose to go back");
                return;
            default:
        }
    }

    private boolean addTask() {
        logger.debug("addTask() method has worked");
        Iterator<LocalDateTime> it = view.addTimeToTaskView().iterator();
        Task task;
        LocalDateTime start = it.next();
        if (!it.hasNext()) {
            task = new Task(view.getTaskTitle(), start);
        } else {
            task = new Task(view.getTaskTitle(),
                    start, it.next(), view.getInterval());
        }
        task.setActive(view.getTaskIsActive());
        logger.debug("Task was added to model");
        model.add(task);
        return true;
    }
    private boolean updateTask(int index) {
        logger.debug("updateTask() method has worked");
        new ShowTaskView(model).printIndexTitleTask();
        Task task = model.getTask(index);
        task.setTitle(view.getTaskTitle());
        task.setActive(view.getTaskIsActive());
        List<LocalDateTime> localDateTimes = view.addTimeToTaskView();
        if (localDateTimes.size() == 2) {
            task.setTime(localDateTimes.get(0),
                    localDateTimes.get(1), view.getInterval());
        } else {
            task.setTime(localDateTimes.get(0));
        }
        logger.debug("Task was updated in model");
        return true;
    }

    private boolean deleteTask(int index) {
        logger.debug("deleteTask() method has worked");
        new ShowTaskView(model).printIndexTitleTask();
        model.remove(model.getTask(index));
        logger.debug("Task was deleted from model");
        return true;
    }
}
