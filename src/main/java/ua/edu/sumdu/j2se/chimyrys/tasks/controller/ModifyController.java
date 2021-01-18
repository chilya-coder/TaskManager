package ua.edu.sumdu.j2se.chimyrys.tasks.controller;

import ua.edu.sumdu.j2se.chimyrys.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.chimyrys.tasks.model.Task;
import ua.edu.sumdu.j2se.chimyrys.tasks.view.ModifyTasksView;
import ua.edu.sumdu.j2se.chimyrys.tasks.view.ShowTaskView;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class ModifyController extends Controller {
    private ModifyTasksView view;
    public ModifyController(AbstractTaskList model) {
        super(model);
        view = new ModifyTasksView();
    }

    @Override
    public void action() {
        TaskAction taskAction;
        try {
            taskAction = view.getTaskActionFromUser(model);
        } catch (NoSuchElementException e) {
            System.out.println("Invalid value was put");
            action();
            return;
        }
        switch (taskAction.getAction()) {
            case ADD:
                addTask();
                break;
            case UPDATE:
                updateTask(taskAction.getTaskIndex());
                break;
            case DELETE:
                deleteTask(taskAction.getTaskIndex());
                break;
            case BACK:
                return;
            default:
        }
    }

    public boolean addTask() {
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
        model.add(task);
        return true;
    }
    public boolean updateTask(int index) {
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
        return true;
    }

    public boolean deleteTask(int index) {
        new ShowTaskView(model).printIndexTitleTask();
        model.remove(model.getTask(index));
        return true;
    }
}
