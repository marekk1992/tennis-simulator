package tennisSimulator;

import java.util.Scanner;

public class Menu {

    public static MatchSimulator matchSimulator = new MatchSimulator();
    public static PlayersDatabase playersDatabase = new PlayersDatabase();
    public static Scanner scanner = new Scanner(System.in);

    public void runTennisSimulator() {
        boolean quit = false;
        System.out.println("WELCOME TO TENNIS SIMULATOR\n");
        printMenu();
        while(!quit) {
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 0:
                    printMenu();
                    break;
                case 1:
                    simulateMatch();
                    break;
                case 2:
                    playersDatabase.printDatabase();
                    break;
                case 3:
                    addPlayerToDatabase();
                    break;
                case 4:
                    quit = true;
                    System.out.println("Exiting simulator...");
                    break;
            }
        }
    }

    private void printMenu() {
        System.out.println("Select:\n" +
                "0 - show options;\n" +
                "1 - simulate match;\n" +
                "2 - show players database;\n" +
                "3 - add player to database;\n" +
                "4 - exit simulator;");
    }

    private void addPlayerToDatabase() {
        System.out.print("Enter a full name: ");
        String name = scanner.nextLine();
        if (!name.contains(" ")) {
            System.out.println("Addition failed. Please enter full player name");
            return;
        }

        System.out.print("Enter a gender (men/women): ");
        String gender = scanner.nextLine().toLowerCase();

        checkGender(gender);
        int rating = scanner.nextInt();

        playersDatabase.addPlayer(new Player(name, gender, rating));
    }

    private void simulateMatch() {
    }

    private void checkGender(String gender) {
        String men = "men";
        if (gender.equals(men)) {
            System.out.print("Enter an ATP rating: ");
        } else {
            System.out.print("Enter a WTA rating: ");
        }
    }
}
