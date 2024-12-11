package view;

import java.util.Scanner;

public class Navigator {
    private Menu currentMenu;
    private Menu rootMenu;

    public Navigator(Menu initialMenu) {
        this.currentMenu = initialMenu;
        this.rootMenu = initialMenu;
    }

    public void printMenu() {
        currentMenu.printMenu();
    }

    public void navigate() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            System.out.print("Choose an option: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit") || input.equals("7")) {
                System.out.println("Exiting... Goodbye!");
                break;
            }

            try {
                int choice = Integer.parseInt(input);
                if (choice < 1 || choice > currentMenu.getMenuItems().size()) {
                    System.out.println("Invalid choice, please try again.");
                } else {
                    MenuItem selectedItem = currentMenu.getMenuItems().get(choice - 1);
                    selectedItem.doAction();
                    if (selectedItem.getNextMenu() != null) {
                        currentMenu = selectedItem.getNextMenu();
                    } else if (selectedItem.getTitle().equalsIgnoreCase("Back to Main Menu")) {
                        currentMenu = rootMenu;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a number.");
            }
        }
    }
}
