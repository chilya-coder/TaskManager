package ua.edu.sumdu.j2se.chimyrys.tasks.controller;

public class TaskAction {
    private ActionChoice action;
    private int taskIndex;

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
}