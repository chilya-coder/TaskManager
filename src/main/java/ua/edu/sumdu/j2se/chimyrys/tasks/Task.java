package ua.edu.sumdu.j2se.chimyrys.tasks;

public class Task {
    private String title;
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean active;

    public Task(String title, int time) {
        this.title = title;
        this.time = time;
        this.interval = 0;
        this.start = 0;
        this.end = 0;
        this.active = false;
    }
    public Task(String title, int start, int end, int interval) {
        this.title = title;
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
    void setActive(boolean active) {
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
        if (isRepeated()) {
            this.start = 0;
            this.end = 0;
            this.interval = 0;
        }
        this.time = time;
    }

    public void setTime(int start, int end, int interval) {
        time = 0;
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
}
