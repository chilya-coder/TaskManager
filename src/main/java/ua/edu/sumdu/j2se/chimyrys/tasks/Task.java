package ua.edu.sumdu.j2se.chimyrys.tasks;

import java.util.Objects;
import java.time.LocalDateTime;

public class Task implements Cloneable {
    private String title;
    private LocalDateTime time;
    private LocalDateTime start;
    private LocalDateTime end;
    private int interval; //секунды
    private boolean active;

    public Task(String title, LocalDateTime time) {
        if (time == null) {
            throw new IllegalArgumentException();
        }
        this.title = title;
        this.time = time;
        this.interval = 0;
        this.start = null;
        this.end = null;
        this.active = false;
    }
    public Task(String title, LocalDateTime start, LocalDateTime end, int interval) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.time = null;
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

    public LocalDateTime getTime() {
        if (isRepeated()) {
            return start;
        } else {
            return time;
        }
    }

    public void setTime(LocalDateTime time) {
        if (isRepeated()) {
            this.start = null;
            this.end = null;
            this.interval = 0;
        }
        this.time = time;
    }

    public void setTime(LocalDateTime start, LocalDateTime end, int interval) {
        if (start.isBefore(LocalDateTime.now()) || end.isBefore(LocalDateTime.now())|| interval < 0) {
            throw new IllegalArgumentException();
        }
        if (!isRepeated()) {
            time.equals(null);
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
    public LocalDateTime getStartTime() {
        if (isRepeated()) {
            return start;
        } else {
            return time;
        }
    }

    public LocalDateTime getEndTime() {
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
    public LocalDateTime nextTimeAfter(LocalDateTime current) {
        if (!isActive()) {
            return null;
        }
        if (!isRepeated()) {
            if (current.isBefore(getTime())) {
                return getTime();
            } else {
                return null;
            }
        } else {
            if (getStartTime().isAfter(current)) {
                return getStartTime();
            } else if (getEndTime().isBefore(current)
                    || getEndTime().isEqual(current)) {
                return null;
            } else {
                for (LocalDateTime i = start; i.isBefore(end)
                        || i.equals(end); i = i.plusSeconds(interval)) {
                    if (i.isAfter(current)) {
                        return i;
                    }
                }
            }
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        if (time == null && task.time == null &&
        start == null && task.start == null &&
        end == null && task.end == null) {
           return interval == task.interval &&
                    active == task.active &&
                    Objects.equals(title, task.title);
        }
        else if (time == task.time && start == task.start &&
                end == task.end) {
            return interval == task.interval &&
                    active == task.active &&
                    Objects.equals(title, task.title);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, active);
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
    }
}