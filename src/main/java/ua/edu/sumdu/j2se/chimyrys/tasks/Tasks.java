package ua.edu.sumdu.j2se.chimyrys.tasks;

import java.time.LocalDateTime;
import java.util.*;

public class Tasks {
    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        Iterator<Task> iterator = tasks.iterator();
        List<Task> collection = new ArrayList<>();
        while (iterator.hasNext()) {
            Task currentTask = iterator.next();
            LocalDateTime nextTimeAfter = currentTask.nextTimeAfter(start);
            if (nextTimeAfter == null) {
                continue;
            }
            if (nextTimeAfter.isBefore(end) || nextTimeAfter.equals(end)) {
                collection.add(currentTask);
            }
        }
        return collection;
    }
    public static SortedMap<LocalDateTime, Set<Task>>
    calendar(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        Set<Task> set = new HashSet<>();
        SortedMap<LocalDateTime, Set<Task>> result = new TreeMap<>();
        Iterator<Task> iterator = tasks.iterator(); // этот итератор указывает на входной tasks
        Iterable<Task> collection = new ArrayList<>();
        collection = incoming(tasks, start, end); // отсортированная коллекция START END
        List <Task> list = new ArrayList<>();
        for (Task task:collection
             ) {
            list.add(task);
        }
        while (iterator.hasNext()) {
            Task currentTask = iterator.next(); //задача с входного tasks
            LocalDateTime currentTime = currentTask.getTime(); // время задачи с этого tasks
            int i = 0;
            for (Task task:collection
                 ) {
                if(currentTime.equals(list.get(i).getTime())) {
                    set.add(currentTask);
                }
                ++i;
            }
            result.put(currentTime,set);
        }
        return result;
    }
}
