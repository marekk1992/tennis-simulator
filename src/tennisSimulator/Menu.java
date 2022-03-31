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
        System.out.print("Enter a full player name: ");
        String name = scanner.nextLine();

        if (!isFullName(name)) {
            System.out.println("Addition failed. Please enter full player name.");
            return;
        }

        System.out.print("Enter a gender (man/woman): ");
        String gender = scanner.nextLine().toLowerCase();

        if (!isValidGender(gender)) {
            System.out.println("Addition failed. Please enter a valid gender.");
            return;
        }

        resolveGender(gender);
        int rating = scanner.nextInt();
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
        return rating >= 0;
    }

    private void resolveGender(String gender) {
        if (gender.equalsIgnoreCase(Player.MAN)) {
            System.out.print("Enter a WTA rating: ");
        } else {
            System.out.print("Enter a WTA rating: ");
        }
    }
}