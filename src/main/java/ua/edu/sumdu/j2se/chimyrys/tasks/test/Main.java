package ua.edu.sumdu.j2se.chimyrys.tasks.test;

import ua.edu.sumdu.j2se.chimyrys.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.chimyrys.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.chimyrys.tasks.model.Task;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        AbstractTaskList list = new ArrayTaskList();
        Task task15 = new Task("Title15", LocalDateTime.now().plusSeconds(15));
        Task task30 = new Task("Title30", LocalDateTime.now().plusSeconds(30));
        Task task45 = new Task("Title45", LocalDateTime.now().plusSeconds(45));
        task15.setActive(true);
        task30.setActive(true);
        task45.setActive(true);
        list.add(task15);
        list.add(task30);
        list.add(task45);
        for (Task task1: list) {
            System.out.println(task1.nextTimeAfter(LocalDateTime.now()));
        }
        for (Task task1: list.incoming(LocalDateTime.now(), LocalDateTime.now().plusMinutes(1))) {
            System.out.println(task1);
        }
    }
}
