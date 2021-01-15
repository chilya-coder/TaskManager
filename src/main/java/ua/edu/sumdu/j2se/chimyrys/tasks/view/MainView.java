package ua.edu.sumdu.j2se.chimyrys.tasks.view;

import ua.edu.sumdu.j2se.chimyrys.tasks.controller.UserChoice;

import java.util.Arrays;
import java.util.Scanner;

public class MainView {
    public void printMainMenu() {
        System.out.println("1. Show all tasks");
        System.out.println("2. Show calendar");
        System.out.println("3. Add/Update task");
        System.out.println("4. Delete task");
        System.out.println("5. Show info about task");
        System.out.println("6. Quit");
    }
    public UserChoice getUserChoice() {
        System.out.print("Enter your choice: ");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        return Arrays.stream(UserChoice.values())
                .filter(choice -> choice.getId() == id)
                .iterator().next();
    }
}
