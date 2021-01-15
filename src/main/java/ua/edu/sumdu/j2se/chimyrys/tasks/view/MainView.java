package ua.edu.sumdu.j2se.chimyrys.tasks.view;

import ua.edu.sumdu.j2se.chimyrys.tasks.controller.UserChoice;

import java.util.Arrays;
import java.util.Scanner;

public class MainView {
    /**
     * Class that provides user with printing main menu and gets user's choice
     * via this menu.
     */
    public void printMainMenu() {
        System.out.println("1. Show all tasks");
        System.out.println("2. Show calendar");
        System.out.println("3. Add/update/delete task");
        System.out.println("4. Show info about task");
        System.out.println("5. Quit");
    }

    /**
     * Method that gets user's choice as int and checks if
     * there is a such value in UserChoice enum
     * @return UserChoice
     */
    public UserChoice getUserChoice() {
        System.out.print("Enter your choice: ");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        return Arrays.stream(UserChoice.values())
                .filter(choice -> choice.getId() == id)
                .iterator().next();
    }
}
