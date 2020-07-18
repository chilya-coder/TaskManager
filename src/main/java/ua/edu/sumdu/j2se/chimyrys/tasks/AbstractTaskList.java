package ua.edu.sumdu.j2se.chimyrys.tasks;

public abstract class AbstractTaskList implements Iterable<Task>, Cloneable {
    public abstract void add(Task task);
    public abstract int size();
    public abstract boolean remove(Task task);
    public abstract Task getTask(int index);
    public AbstractTaskList incoming(int from, int to) {
        AbstractTaskList answerList = new ArrayTaskList();
        for (int i = 0; i < size(); ++i) {
            int taskNextTime = getTask(i).nextTimeAfter(from);
            if (taskNextTime <= to && taskNextTime != -1) {
                answerList.add(getTask(i));
            }
        }
        return answerList;
    }
}
