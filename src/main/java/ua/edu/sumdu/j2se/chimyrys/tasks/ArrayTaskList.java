package ua.edu.sumdu.j2se.chimyrys.tasks;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Stream;

public class ArrayTaskList extends AbstractTaskList {
    private Task[] tasks;
    private final int START_LENGTH = 4;
    private int length;

    public ArrayTaskList() {
        tasks = new Task[START_LENGTH];
        length = 0;
    }
    private void resize() {
        Task[] newTasks = new Task[length * 2];
        for (int i = 0; i < tasks.length; ++i) {
            newTasks[i] = getTask(i);
        }
        tasks = newTasks;
    }
    @Override
    public void add(Task task) {
        if (size() >= tasks.length) {
            resize();
        }
        tasks[length] = task;
        length++;
    }
    @Override
    public int size() {
        return length;
    }
    @Override
    public boolean remove(Task task) {
        for (int i = 0; i < size(); ++i) {
            if (getTask(i).equals(task)) {
                for (int j = i; j < size() - 1; ++j) {
                    tasks[j] = getTask(j + 1);
                }
                tasks[size() - 1] = null;
                length--;
                return true;
            }
        }
        return false;
    }
    @Override
    public Task getTask(int index) {
        if (index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        return tasks[index];
    }

    @Override
    public Stream<Task> getStream() {
            Stream<Task> stream = Arrays.stream(tasks).filter(x -> x != null);
            return stream;
    }

    @Override
    public Iterator<Task> iterator() {
        return new ArrayTaskListIterator();
    }
    private class ArrayTaskListIterator implements Iterator<Task> {
        private int currentIndex = 0;
        @Override
        public boolean hasNext() {
            if (currentIndex == size()) {
                return false;
            }
            if (tasks[currentIndex] == null) {
                return false;
            } else {
                return true;
            }
        }

        @Override
        public Task next() {
            Task arrayTask = tasks[currentIndex];
            currentIndex++;
            return arrayTask;
        }

        @Override
        public void remove() {
            if (currentIndex == 0) {
                throw new IllegalStateException();
            }
            for (int i = currentIndex - 1; i < size() - 1; ++i) {
                tasks[i] = tasks[i + 1];
            }
            tasks[size() - 1] = null;
            currentIndex--;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        ArrayTaskList list = (ArrayTaskList) obj;
        if (list.size() != this.size()) {
            return false;
        }
        for (int i = 0; i < size(); ++i) {
            if (this.getTask(i) == null && list.getTask(i) == null) {
                return true;
            }
        }
        for (int i = 0; i < size(); ++i) {
                if (!this.getTask(i).equals(list.getTask(i))) {
                    return false;
                }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(START_LENGTH, length);
        result = 31 * result + Arrays.hashCode(tasks);
        return result;
    }

    public Object clone() throws CloneNotSupportedException {
        ArrayTaskList arrayTaskList = (ArrayTaskList) super.clone();
        arrayTaskList.tasks = new Task[length];
        arrayTaskList.length = 0;
        for (int i = 0; i < size(); ++i) {
            arrayTaskList.add((Task) this.getTask(i).clone());
        }
        return arrayTaskList;
    }
}

