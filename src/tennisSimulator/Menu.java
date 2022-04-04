package tennisSimulator;

import java.util.Scanner;

public class Menu {

    private static final PlayersDatabase playersDatabase = new PlayersDatabase();
    private static final Scanner scanner = new Scanner(System.in);

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
                default:
                    System.out.println("Incorrect input. Please choose one of available options.\n");
                    printMenu();
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
        String name = promptUserForName();
        if (!isFullName(name)) {
            System.out.println("Addition failed. Please enter full player name.");
            return;
        }

        String gender = promptUserForGender();
        if (!isValidGender(gender)) {
            System.out.println("Addition failed. Please enter a valid gender.");
            return;
        }

        String organization = resolveOrganization(gender);
        int rating = promptUserForRating(organization);
        if (!isValidRating(rating)) {
            System.out.println("Addition failed. Please enter a valid rating.");
            return;
        }

        playersDatabase.addPlayer(new Player(name, gender, rating));
    }

    private boolean isValidGender(String gender) {
        return gender.equalsIgnoreCase(Player.MAN) || gender.equalsIgnoreCase(Player.WOMAN);
    }

    private boolean isFullName(String name) {
        return name.contains(" ");
    }

    private boolean isValidRating(int rating) {
        return rating > 0;
    }

    private String resolveOrganization(String gender) {
        if (gender.equalsIgnoreCase(Player.MAN)) {
            return "ATP";
        } else {
            return "WTA";
        }
    }

    private int promptUserForRating(String organization) {
        if (organization.equalsIgnoreCase(Player.MEN_ORGANIZATION)) {
            System.out.print("Enter a ATP rating: ");
        } else {
            System.out.print("Enter a WTA rating: ");
        }
        return scanner.nextInt();
    }

    private String promptUserForName() {
        System.out.print("Enter a full player name: ");
        return scanner.nextLine();
    }

    private String promptUserForGender() {
        System.out.print("Enter a gender (man/woman): ");
        return scanner.nextLine();
    }
}
