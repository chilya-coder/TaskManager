package ua.edu.sumdu.j2se.chimyrys.tasks;

import java.util.Objects;

public class Task implements Cloneable{
    private String title;
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean active;

    public Task(String title, int time) {
        this.title = title;
        if (time < 0) {
            throw new IllegalArgumentException();
        }
        this.time = time;
        this.interval = 0;
        this.start = 0;
        this.end = 0;
        this.active = false;
    }
    public Task(String title, int start, int end, int interval) {
        this.title = title;
        if (start < 0 || end < 0 || interval <0) {
            throw new IllegalArgumentException();
        }
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.time = 0;
        this.active = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    public int getTime() {
        if (isRepeated()) {
            return start;
        } else {
            return time;
        }
    }

    public void setTime(int time) {
        if (time < 0) {
            throw new IllegalArgumentException();
        }
        if (isRepeated()) {
            this.start = 0;
            this.end = 0;
            this.interval = 0;
        }
        this.time = time;
    }

    public void setTime(int start, int end, int interval) {
        if (start < 0 || end < 0 || interval < 0) {
            throw new IllegalArgumentException();
        }
        if (!isRepeated()) {
            time = 0;
        }
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    public boolean isRepeated() {
        if (interval == 0) {
            return false;
        } else {
            return true;
        }
    }
    public int getStartTime() {
        if (isRepeated()) {
            return start;
        } else {
            return time;
        }
    }

    public int getEndTime() {
        if (isRepeated()) {
            return end;
        } else {
            return time;
        }
    }

    public int getRepeatInterval() {
        if (!isRepeated()) {
            return 0;
        } else {
            return interval;
        }
    }
    public int nextTimeAfter (int current) {
        if (!isActive()) {
            return -1;
        }
        if (!isRepeated()) {
            if (current < getTime()) {
                return getTime();
            } else {
                return -1;
            }
        }
        else {
            if (getStartTime() > current) {
                return getStartTime();
            } else if (getEndTime() <= current) {
                return -1;
            } else {
                for (int i = start; i < end ; i += interval) {
                    if (i > current) {
                        return i;
                    }
                }
            }
            return -1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return time == task.time &&
                start == task.start &&
                end == task.end &&
                interval == task.interval &&
                active == task.active &&
                Objects.equals(title, task.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, time, start, end, interval, active);
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", time=" + time +
                ", start=" + start +
                ", end=" + end +
                ", interval=" + interval +
                ", active=" + active +
                '}';
    }
    public Object clone() throws CloneNotSupportedException {
        Task clonedTask = (Task) super.clone();

        return clonedTask;
        /*if (this.time != 0) {
            return new Task(this.title, this.time);
        }
        return new Task(this.title, this.start, this.end, this.interval);*/

    }
}
