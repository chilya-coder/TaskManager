package ua.edu.sumdu.j2se.chimyrys.tasks.view;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.chimyrys.tasks.controller.MainController;
import ua.edu.sumdu.j2se.chimyrys.tasks.model.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.stream.Collectors;

public class CalendarView {
    final static Logger logger = Logger.getLogger(MainController.class);

    public List<LocalDateTime> getCalendarTimeFromUser() {
        logger.debug("getCalendarTimeFromUser has worked");
        List<LocalDateTime> localDateTimes = new ArrayList<>();
        ModifyTasksView modifyTasksView = new ModifyTasksView();
        System.out.println("Enter start date for calendar:");
        LocalDateTime startCalendar = modifyTasksView.getTaskTime();
        System.out.println("Enter end date for calendar:");
        LocalDateTime endCalendar = modifyTasksView.getTaskTime();
        localDateTimes.add(startCalendar);
        localDateTimes.add(endCalendar);
        localDateTimes.sort(LocalDateTime::compareTo);
        logger.info("User entered end and start date for calendar");
        return localDateTimes;
    }
    public void printCalendar(SortedMap<LocalDateTime, Set<Task>> calendar) {
        logger.debug("printCalendar method has worked");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Set<Map.Entry<LocalDateTime, Set<Task>>> entries = calendar.entrySet();
        for (Map.Entry<LocalDateTime, Set<Task>> entry : entries) {
            System.out.println(entry.getKey().format(formatter) + " - "
                    + entry.getValue().stream().map(Task::getTitle).collect(Collectors.joining(", ")));
        }
    }
}
