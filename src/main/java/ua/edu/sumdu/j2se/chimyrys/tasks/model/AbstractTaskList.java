package ua.edu.sumdu.j2se.chimyrys.tasks.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.stream.Stream;

public abstract class AbstractTaskList implements Iterable<Task>, Cloneable, Serializable {
    public abstract void add(Task task);
    public abstract int size();
    public abstract boolean remove(Task task);
    public abstract Task getTask(int index);
    public abstract Stream<Task> getStream();
    public final AbstractTaskList incoming(LocalDateTime from, LocalDateTime to) {
        AbstractTaskList answerList = new ArrayTaskList();
        getStream()
                .filter(task -> task.nextTimeAfter(from) != null)
                .filter(task -> task.nextTimeAfter(from).isBefore(to))
                .forEach(answerList::add);
        return answerList;
    }
}