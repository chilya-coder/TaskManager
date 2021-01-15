package ua.edu.sumdu.j2se.chimyrys.tasks.model;

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
        SortedMap<LocalDateTime, Set<Task>> result = new TreeMap<>();
        Iterator<Task> taskIterator = incoming(tasks, start, end).iterator();
        while (taskIterator.hasNext()) {
            Task currentTask = taskIterator.next();
                Set<Map.Entry<LocalDateTime, Set<Task>>> entries = result.entrySet();
                LocalDateTime currentStartTime = currentTask.nextTimeAfter(start);
                while ((currentStartTime.isBefore(end) || currentStartTime.equals(end))
                        && (currentStartTime.isAfter(start) ||  currentStartTime.equals(start))) {
                    Set<Task> currentDateSet = new HashSet<>();
                    if (!result.isEmpty()) {
                        for (Map.Entry<LocalDateTime, Set<Task>> entry : entries) {
                            if (entry.getKey().equals(currentStartTime)) {
                                currentDateSet = result.get(currentStartTime);
                                currentDateSet.add(currentTask);
                            }
                        }
                    }
                    currentDateSet.add(currentTask);
                    result.put(currentStartTime, currentDateSet);
                    currentStartTime = currentStartTime.plusSeconds(currentTask.getRepeatInterval());
            }
        }
        return result;
    }
}
