package ua.edu.sumdu.j2se.chimyrys.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.chimyrys.tasks.StringUtils;
import ua.edu.sumdu.j2se.chimyrys.tasks.model.AbstractTaskList;

public abstract class Controller {
    /**
     * Abstract class that consists of AbstractTaskList model and action() method
     */
    protected AbstractTaskList model;
    final static Logger logger = Logger.getLogger(MainController.class);
    public abstract void action();
    public Controller() {
        super();
    }
    public Controller(AbstractTaskList model) {
        logger.info(this.getClass() + StringUtils.CONSTRUCTOR_WORKED);
        this.model = model;
    }
}
