package ua.edu.sumdu.j2se.chimyrys.tasks.controller;

public enum ActionChoice {
    ADD(1),
    UPDATE(2),
    DELETE(3),
    BACK(4);
    protected int id;
    public int getId() {
        return id;
    }
    ActionChoice(int id) {
        this.id = id;
    }
}
