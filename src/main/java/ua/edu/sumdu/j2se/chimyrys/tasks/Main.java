package ua.edu.sumdu.j2se.chimyrys.tasks;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.chimyrys.tasks.controller.MainController;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);
    public static void main(String[] args) {
        logger.info("Program has started");
        MainController controller = new MainController();
    }
}
