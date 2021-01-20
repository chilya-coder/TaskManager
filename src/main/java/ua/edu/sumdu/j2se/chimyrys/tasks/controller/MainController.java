package ua.edu.sumdu.j2se.chimyrys.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.chimyrys.tasks.StringUtils;
import ua.edu.sumdu.j2se.chimyrys.tasks.view.MainView;

import java.util.NoSuchElementException;

public class MainController extends Controller {
    private final static Logger logger = Logger.getLogger(MainController.class);
    private MainView view;

    public MainController() {
        super();
        model = DataReadWriteController.readData();
        NotifyController notifyController = new NotifyController(model);
        notifyController.setDaemon(true);
        notifyController.start();
        view = new MainView();
        action();
    }
    /**
     * Method that calls specified controller via
     * user's choice provided in MainView.
     * Throws NoSuchElementException if user's choice was invalid.
     * Throws Exception if Controller object stays null
     */
    @Override
    public void action() {
        logger.debug(StringUtils.ACTION_WORKED);
        do {
            view.printMainMenu();
            UserChoice userChoice;
            try {
                userChoice = view.getUserChoice();
            } catch (NoSuchElementException e) {
                logger.error(StringUtils.INVALID_INPUT_VALUE);
                System.out.println(StringUtils.INVALID_INPUT_VALUE);
                action();
                return;
            }
            Controller controller = this;
            logger.debug("Main menu has worked");
            switch (userChoice) {
                case SHOW_ALL_TASKS:
                    controller = new ShowAllTasksController(model);
                    logger.info("User chose to show all tasks");
                    break;
                case SHOW_CALENDAR:
                    controller = new CalendarController(model);
                    logger.info("User chose to show calendar");
                    break;
                case ADD_UPDATE_DELETE_TASK:
                    controller = new ModifyController(model);
                    logger.info("User chose to add/update/delete task");
                    break;
                case SHOW_TASK_INFO:
                    controller = new TaskInfoController(model);
                    logger.info("User chose to show detailed info about task");
                    break;
                case QUIT:
                    System.out.println("See you later!");
                    logger.info("User chose to quit");
                    System.exit(0);
                    break;
            }
            controller.action();
            DataReadWriteController.writeData(model);
            logger.debug("Tasks' data has been put into file");
        } while (true);
    }
}
