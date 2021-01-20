package ua.edu.sumdu.j2se.chimyrys.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.chimyrys.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.chimyrys.tasks.model.Task;
import ua.edu.sumdu.j2se.chimyrys.tasks.view.NotifyView;

import java.time.LocalDateTime;
import java.util.Arrays;

public class NotifyController extends Thread {
    private AbstractTaskList model;
    private NotifyView view;
    private final static Logger logger = Logger.getLogger(NotifyController.class);
    public NotifyController(AbstractTaskList model) {
        this.model = model;
        this.view = new NotifyView();
    }
    @Override
    public void run() {
        try {
            do {
                AbstractTaskList incoming = model.incoming(LocalDateTime.now(),
                        LocalDateTime.now().plusMinutes(1));
                if (incoming.size() != 0) {
                    view.printNotify(incoming);
                    logger.info("Tasks " + Arrays.toString(incoming.getStream().map(Task::getTitle).toArray()) + " start");

                }
                sleep(1000 * 60);
            } while (true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
