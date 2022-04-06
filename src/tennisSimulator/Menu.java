package tennisSimulator;

import java.util.Scanner;

public class Menu {

    private static final PlayersDatabase playersDatabase = new PlayersDatabase();
    private static final Scanner scanner = new Scanner(System.in);
    private static MatchSimulator matchSimulator;

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
                    openMatchMenu();
                    break;
                case 4:
                    quit = true;
                    System.out.println("Exiting simulator.....");
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
                "3 - open match menu;\n" +
                "4 - exit simulator;");
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
        return gender.equalsIgnoreCase(Gender.MAN.toString())
                || gender.equalsIgnoreCase(Gender.WOMAN.toString());
    }

    private boolean isFullName(String name) {
        return name.contains(" ");
    }

    private boolean isValidRating(int rating) {
        return rating > 0;
    }

    private String resolveOrganization(String gender) {
        if (gender.equalsIgnoreCase(Gender.MAN.toString())) {
            return String.valueOf(Organization.ATP.toString());
        } else {
            return String.valueOf(Organization.WTA.toString());
        }
    }

    private int promptUserForRating(String organization) {
        if (organization.equalsIgnoreCase(Organization.ATP.toString())) {
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

    private void openMatchMenu() {
        boolean quit = false;
        System.out.println("Opening match menu.......");
        printMatchOptions();
        while (!quit) {
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 0:
                    printMatchOptions();
                    break;
                case 1:
                    simulateMatch();
                    break;
                case 2:
                    quit = true;
                    System.out.println("Going back to tennis simulator menu.......");
                    printMenu();
                    break;
                default:
                    System.out.println("Incorrect input. Please choose one of available options.\n");
                    printMatchOptions();
                    break;
            }
        }
    }

    private void printMatchOptions() {
        System.out.println("Select:\n" +
                "0 - show match options;\n" +
                "1 - start simulation;\n" +
                "2 - exit match menu;");
    }

    private boolean addedPlayersToMatchSimulator() {
        if (playersDatabase.getPlayers().isEmpty()) {
            System.out.println("Players database is empty.");
            return false;
        }

        System.out.println("Available players: ");
        playersDatabase.print();

        System.out.print("Enter a first player name: ");
        String firstPlayerName = scanner.nextLine();
        if (!isValidPlayer(firstPlayerName)) {
            System.out.println("Can`t find player in database. Please enter valid name.");
            return false;
        }
        Player firstPlayer = resolvePlayer(firstPlayerName);

        System.out.print("Enter a second player name: ");
        String secondPlayerName = scanner.nextLine();
        if (!isValidPlayer(secondPlayerName)) {
            System.out.println("Can`t find player in database. Please enter valid name.");
            return false;
        }
        Player secondPlayer = resolvePlayer(secondPlayerName);

        matchSimulator = new MatchSimulator(firstPlayer, secondPlayer);
        return true;
    }

    private boolean isValidPlayer(String name) {
        return playersDatabase.findPlayer(name) != null;
    }

    private Player resolvePlayer(String name) {
        return playersDatabase.findPlayer(name);
    }

    private void simulateMatch() {
        if (!addedPlayersToMatchSimulator()) {
            return;
        }

        Player firstPlayer = matchSimulator.getFirstPlayer();
        Player secondPlayer = matchSimulator.getSecondPlayer();

        System.out.println("\n" + firstPlayer.getName() + " is playing against "
                + secondPlayer.getName() + "\n");

        matchSimulator.setFirstServer();
        boolean isWinner = false;
        while (!isWinner) {
            showMatchResult(firstPlayer, secondPlayer);
            isWinner = true;
        }
    }

    private void showMatchResult(Player firstPlayer, Player secondPlayer) {
        System.out.println("\t\t  GAME \t SET \tMATCH");
        System.out.println(firstPlayer.getAbbreviatedName() + "  " + 15 + "\t   "
                + firstPlayer.getGamesWon() + "\t  " + firstPlayer.getSetsWon());
        System.out.println(secondPlayer.getAbbreviatedName() + "  " + 40 + "\t   "
                + secondPlayer.getGamesWon() + "\t  " + secondPlayer.getSetsWon());
    }
}
