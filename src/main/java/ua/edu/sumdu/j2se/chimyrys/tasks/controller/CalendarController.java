package ua.edu.sumdu.j2se.chimyrys.tasks.controller;

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
    }
    @Override
    public void action() {
        List<LocalDateTime> localDateTimes = calendarView.getCalendarTimeFromUser();
        calendarView.printCalendar(Tasks.calendar(model, localDateTimes.get(0), localDateTimes.get(1)));
    }
}
