package ua.edu.sumdu.j2se.chimyrys.tasks.controller;

public class TaskAction {
    private ActionChoice action;
    private int taskIndex;

    public TaskAction(ActionChoice action, int taskIndex) {
        this.action = action;
        this.taskIndex = taskIndex;
    }
    public TaskAction(ActionChoice action) {
        this.action = action;
        taskIndex = -1;
    }

    public ActionChoice getAction() {
        return action;
    }

    public void setAction(ActionChoice action) {
        this.action = action;
    }

    public int getTaskIndex() {
        return taskIndex;
    }

    public void setTaskIndex(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String toString() {
        return "TaskAction{" +
                "action=" + action +
                ", taskIndex=" + taskIndex +
                '}';
    }
}