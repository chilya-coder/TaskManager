package ua.edu.sumdu.j2se.chimyrys.tasks.view;

import ua.edu.sumdu.j2se.chimyrys.tasks.model.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;
import java.util.stream.Collectors;

public class CalendarView {
    private Scanner scanner;
    public CalendarView() {
        scanner = new Scanner(System.in);
    }
    public List<LocalDateTime> getCalendarTimeFromUser() {
        List<LocalDateTime> localDateTimes = new ArrayList<>();
        ModifyTasksView modifyTasksView = new ModifyTasksView();
        System.out.println("Enter start date for calendar:");
        LocalDateTime startCalendar = modifyTasksView.getTaskTime();
        System.out.println("Enter end date for calendar:");
        LocalDateTime endCalendar = modifyTasksView.getTaskTime();
        localDateTimes.add(startCalendar);
        localDateTimes.add(endCalendar);
        localDateTimes.sort(LocalDateTime::compareTo);
        return localDateTimes;
    }
    public void printCalendar(SortedMap<LocalDateTime, Set<Task>> calendar) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Set<Map.Entry<LocalDateTime, Set<Task>>> entries = calendar.entrySet();
        for (Map.Entry<LocalDateTime, Set<Task>> entry : entries) {
            System.out.println(entry.getKey().format(formatter) + " - "
                    + entry.getValue().stream().map(Task::getTitle).collect(Collectors.joining(", ")));
        }
    }
}
