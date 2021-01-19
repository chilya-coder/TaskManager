package ua.edu.sumdu.j2se.chimyrys.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.chimyrys.tasks.StringUtils;
import ua.edu.sumdu.j2se.chimyrys.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.chimyrys.tasks.model.Tasks;
import ua.edu.sumdu.j2se.chimyrys.tasks.view.CalendarView;

import java.time.LocalDateTime;
import java.util.List;


public class CalendarController extends Controller {
    private CalendarView calendarView;
    public CalendarController(AbstractTaskList model) {
        super(model);
        calendarView = new CalendarView();
        logger.info(this.getClass() + StringUtils.CONSTRUCTOR_WORKED);
    }
    @Override
    public void action() {
        logger.info(StringUtils.ACTION_WORKED);
        List<LocalDateTime> localDateTimes = calendarView.getCalendarTimeFromUser();
        logger.info("Program got list of start and end calendar LocalDateTimes");
        calendarView.printCalendar(Tasks.calendar(model, localDateTimes.get(0), localDateTimes.get(1)));
        logger.info("Calendar is printed");
    }
}
