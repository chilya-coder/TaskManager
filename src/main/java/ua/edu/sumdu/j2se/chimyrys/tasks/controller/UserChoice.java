package ua.edu.sumdu.j2se.chimyrys.tasks.controller;

public enum UserChoice {
    SHOW_ALL_TASKS(1), SHOW_CALENDAR(2), ADD_UPDATE_DELETE_TASK(3),
    SHOW_TASK_INFO(4), QUIT(5);
    private int id;
    public int getId() {
        return id;
    }
    UserChoice(int id) {
        this.id = id;
    }
}
