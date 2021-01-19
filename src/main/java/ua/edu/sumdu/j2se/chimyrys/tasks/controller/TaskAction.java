package ua.edu.sumdu.j2se.chimyrys.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.chimyrys.tasks.StringUtils;

public class TaskAction {
    private ActionChoice action;
    private int taskIndex;
    final static Logger logger = Logger.getLogger(MainController.class);

    public TaskAction(ActionChoice action, int taskIndex) {
        this.action = action;
        this.taskIndex = taskIndex;
        logger.info(this.getClass() + StringUtils.CONSTRUCTOR_WORKED);
    }
    public TaskAction(ActionChoice action) {
        this.action = action;
        taskIndex = -1;
        logger.info(this.getClass() + StringUtils.CONSTRUCTOR_WORKED);
    }

    public ActionChoice getAction() {
        logger.info(this.getClass() + " getAction method worked");
        return action;
    }

    public void setAction(ActionChoice action) {
        this.action = action;
        logger.info(this.getClass() + " setAction method worked");
    }

    public int getTaskIndex() {
        logger.info(this.getClass() + " getTaskIndex method worked");
        return taskIndex;
    }

    public void setTaskIndex(int taskIndex) {
        logger.info(this.getClass() + " setTaskIndex method worked");
        this.taskIndex = taskIndex;
    }
}