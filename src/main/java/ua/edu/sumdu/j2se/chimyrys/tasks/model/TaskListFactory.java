package ua.edu.sumdu.j2se.chimyrys.tasks.model;

public class TaskListFactory {
    public static AbstractTaskList createTaskList(ListTypes.types type) {
        if (type.toString().equals("ARRAY")) {
            return new ArrayTaskList();
        } else if (type.toString().equals("LINKED")) {
            return new LinkedTaskList();
        }
        return null;
    }
}
