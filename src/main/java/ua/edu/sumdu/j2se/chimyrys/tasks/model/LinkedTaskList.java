package ua.edu.sumdu.j2se.chimyrys.tasks.model;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Stream;

public class LinkedTaskList extends AbstractTaskList {
    private Node firstNode;
    private Node lastNode;

    private class Node implements Cloneable {
        private Task task;
        private Node nextNode;
        public Node(Task task) {
            this.task = task;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node node = (Node) o;
            return Objects.equals(task, node.task)
                    && Objects.equals(nextNode, node.nextNode);
        }

        @Override
        public int hashCode() {
            return Objects.hash(task, nextNode);
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
           Node node = (Node) super.clone();
            node.task = (Task) task.clone();
           if (nextNode != null) {
               node.nextNode = (Node) nextNode.clone();
           }
           return node;
        }
    }
    @Override
    public void add(Task task) {
        if (firstNode == null) {
            firstNode = new Node(task);
            lastNode = firstNode;
            return;
        }
        Node nextNode = new Node(task);
        lastNode.nextNode = nextNode;
        lastNode = nextNode;
    }
    @Override
    public int size() {
        if (firstNode == null) {
            return 0;
        }
        if (firstNode == lastNode) {
            return 1;
        }
        int listSize = 1;
        Node currentNode = firstNode;
        while (currentNode.nextNode != null) {
            listSize++;
            currentNode = currentNode.nextNode;
        }
        return listSize;
    }
    @Override
    public Task getTask(int index) {
        if (index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        Node current = firstNode;
        for (int i = 0; i < size(); ++i) {
            if (i == index) {
                return current.task;
            }
            current = current.nextNode;
        }
        return null;
    }

    @Override
    public Stream<Task> getStream() {
        Task [] list = new Task [size()];
        Iterator<Task> it = iterator();
        for (int i = 0; it.hasNext(); ++i) {
            list[i] = it.next();
        }
        Stream<Task> stream = Arrays.stream(list);
        return stream;
    }

    @Override
    public boolean remove(Task task) {
        Node current = firstNode;
        if (current.task.equals(task)) {
            firstNode = current.nextNode;
            return true;
        }
        for (int i = 0; i < size(); ++i) {
            if (current.nextNode.task.equals(task)) {
                if (current.nextNode == lastNode) {
                    lastNode = current;
                    lastNode.nextNode = null;
                    return true;
                }
                current.nextNode = current.nextNode.nextNode;
                return true;
            }
            current = current.nextNode;
        }
        return false;
    }
    @Override
    public Iterator iterator() {
        return new LinkedTaskListIterator();
    }
    private class LinkedTaskListIterator implements Iterator<Task> {
        private Node currentNode = firstNode;
        private Task currentTask;
        private Node previousNode = null;
        private Node startNode = firstNode;
        @Override
        public boolean hasNext() {
            return startNode.nextNode != null;
        }

        @Override
        public Task next() {
            previousNode = currentNode;
            startNode = currentNode;
            currentTask = currentNode.task;
            currentNode = currentNode.nextNode;
            return currentTask;
        }

        @Override
        public void remove() {
            if (previousNode == null) {
                throw new IllegalStateException();
            }
            if (previousNode.equals(firstNode)) {
                firstNode = firstNode.nextNode;
                return;
            }
            if (currentTask.equals(lastNode.task)) {
                Node dummyNode = firstNode;
                while (!(dummyNode.nextNode.equals(lastNode))) {
                    dummyNode = dummyNode.nextNode;
                }
                lastNode = dummyNode;
                dummyNode.nextNode = null;
            }
            if (previousNode.nextNode.equals(currentNode)) {
                Node dummyNode = firstNode;
                while (!(dummyNode.nextNode.equals(previousNode))) {
                    dummyNode = dummyNode.nextNode;
                }
                dummyNode.nextNode = previousNode.nextNode;
            }
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LinkedTaskList that = (LinkedTaskList) o;
        return Objects.equals(firstNode, that.firstNode)
                && Objects.equals(lastNode, that.lastNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstNode, lastNode);
    }

    public Object clone() throws CloneNotSupportedException {
        LinkedTaskList linkedTaskList = (LinkedTaskList) super.clone();
        linkedTaskList.firstNode = (Node) firstNode.clone();
        Node someNode = linkedTaskList.firstNode;
        for (int i = 0; i < size(); ++i) {
            if (someNode.nextNode.equals(lastNode)) {
                linkedTaskList.lastNode = someNode.nextNode;
                return linkedTaskList;
            }
            someNode = someNode.nextNode;
        }
        return linkedTaskList;
    }
}
