package ua.edu.sumdu.j2se.chimyrys.tasks;

import java.time.LocalDateTime;
import java.util.stream.Stream;

public abstract class AbstractTaskList implements Iterable<Task>, Cloneable {
    public abstract void add(Task task);
    public abstract int size();
    public abstract boolean remove(Task task);
    public abstract Task getTask(int index);
    public abstract Stream<Task> getStream();
    public final AbstractTaskList incoming(LocalDateTime from, LocalDateTime to) {
        AbstractTaskList answerList = new ArrayTaskList();
        for (int i = 0; i < size(); ++i) {
            LocalDateTime taskNextTime = getTask(i).nextTimeAfter(from);
            if (taskNextTime.isBefore(to) && !taskNextTime.equals(null)) {
                answerList.add(getTask(i));
            }
        }
        return answerList;
    }
}
