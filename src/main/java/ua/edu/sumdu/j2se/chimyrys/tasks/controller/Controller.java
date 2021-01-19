package ua.edu.sumdu.j2se.chimyrys.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.chimyrys.tasks.model.AbstractTaskList;

public abstract class Controller {
    /**
     * Abstract class that consists of AbstractTaskList model and action() method
     */
    protected AbstractTaskList model;
    private final static Logger logger = Logger.getLogger(Controller.class);
    public abstract void action();
    public Controller() {
        super();
    }
    public Controller(AbstractTaskList model) {
        this.model = model;
    }
}
