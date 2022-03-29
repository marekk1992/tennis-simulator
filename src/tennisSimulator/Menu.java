package tennisSimulator;

import java.util.Scanner;

public class Menu {

    private static PlayersDatabase playersDatabase = new PlayersDatabase();
    private static Scanner scanner = new Scanner(System.in);

    public void openTennisSimulatorMenu() {
        boolean quit = false;
        System.out.println("WELCOME TO TENNIS SIMULATOR\n");
        printMenu();
        while (!quit) {
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 0:
                    printMenu();
                    break;
                case 1:
                    playersDatabase.print();
                    break;
                case 2:
                    addPlayerToDatabase();
                    break;
                case 3:
                    quit = true;
                    System.out.println("Exiting simulator...");
                    break;
            }
        }
    }

    private void printMenu() {
        System.out.println("Select:\n" +
                "0 - show options;\n" +
                "1 - show players database;\n" +
                "2 - add player to database;\n" +
                "3 - exit simulator;");
    }

    private void addPlayerToDatabase() {
        System.out.print("Enter a full name: ");
        String name = scanner.nextLine();
        if (!name.contains(" ")) {
            System.out.println("Addition failed. Please enter full player name");
            return;
        }

        System.out.print("Enter a gender (man/woman): ");
        String gender = scanner.nextLine().toLowerCase();

        if (defineOrganization(gender)) {
            int rating = scanner.nextInt();
            playersDatabase.addPlayer(new Player(name, gender, rating));
        }
    }

    private boolean defineOrganization(String gender) {
        if (gender.equalsIgnoreCase(Player.MAN)) {
            System.out.print("Enter an ATP rating: ");
            return true;
        } else if (gender.equalsIgnoreCase(Player.WOMAN)){
            System.out.print("Enter a WTA rating: ");
            return true;
        } else {
            System.out.println("Addition failed. Please enter a valid gender.");
            return false;
        }
    }
}
