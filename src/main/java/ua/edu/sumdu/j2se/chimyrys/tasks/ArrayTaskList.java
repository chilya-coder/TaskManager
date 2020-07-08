package ua.edu.sumdu.j2se.chimyrys.tasks;

public class ArrayTaskList {
    private Task[] tasks;
    private final int START_LENGTH = 16;
    private int length;

    public ArrayTaskList() {
        tasks = new Task[START_LENGTH];
        length = 0;
    }

    public void resize() {
        Task[] newTasks = new Task[length * 2];
        for (int i = 0; i < tasks.length; ++i) {
            newTasks[i] = getTask(i);
        }
        tasks = newTasks;
    }

    public void add(Task task) {
        if (size() >= tasks.length) {
            resize();
        }
        tasks[length] = task;
        length++;
    }

    public int size() {
        return length;
    }

    public boolean remove(Task task) {
        for (int i = 0; i < size(); ++i) {
            if (getTask(i).equals(task)) {
                for (int j = i; j <= size() - i; ++j) {
                    tasks[j] = getTask(j + 1);
                }
                length--;
                return true;
            }
        }
        return false;
    }
    public Task getTask(int index) {
       return tasks[index];
    }
    public ArrayTaskList incoming(int from, int to) {
        ArrayTaskList answerList = new ArrayTaskList();
        for (int i = 0; i < size(); ++i) {
            int taskNextTime = getTask(i).nextTimeAfter(from);
            if (taskNextTime <= to && taskNextTime != -1) {
                answerList.add(getTask(i));
            }
        }
        return answerList;
    }
}

