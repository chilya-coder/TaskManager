package ua.edu.sumdu.j2se.chimyrys.tasks.controller;

import ua.edu.sumdu.j2se.chimyrys.tasks.model.AbstractTaskList;

public abstract class Controller {
    protected AbstractTaskList model;
    public abstract void action();
    public Controller() {
        action();
    }
}
