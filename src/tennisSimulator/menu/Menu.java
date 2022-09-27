package tennisSimulator.menu;

import tennisSimulator.match.Match;
import tennisSimulator.player.Gender;
import tennisSimulator.player.Organization;
import tennisSimulator.player.Player;
import tennisSimulator.player.PlayersDatabase;

import java.util.Scanner;

public class Menu {

    private final PlayersDatabase playersDatabase;
    private static final Scanner scanner = new Scanner(System.in);
    private Match match;

    public Menu() {
        playersDatabase = PlayersDatabase.getInstance();
    }

    public void open() {
        boolean quit = false;
        System.out.println("WELCOME TO TENNIS SIMULATOR\n");
        print();
        while (!quit) {
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 0:
                    print();
                    break;
                case 1:
                    playersDatabase.print();
                    break;
                case 2:
                    addPlayerToDatabase();
                    break;
                case 3:
                    simulateMatch();
                    break;
                case 4:
                    quit = true;
                    System.out.println("Exiting simulator.....");
                    break;
                default:
                    System.out.println("Incorrect input. Please choose one of available options.\n");
                    print();
                    break;
            }
        }
    }

    private void print() {
        System.out.println("Select:\n" +
                "0 - show options;\n" +
                "1 - show players database;\n" +
                "2 - add player to database;\n" +
                "3 - simulate match;\n" +
                "4 - exit simulator;");
    }

    private void addPlayerToDatabase() {
        String name = promptUserForName();
        if (!isFullName(name)) {
            System.out.println("Addition failed. Please enter full player name.");
            return;
        }

        String playerGender = promptUserForGender();
        if (!isValidGender(playerGender)) {
            System.out.println("Addition failed. Please enter a valid gender.");
            return;
        }
        Gender gender = resolveGender(playerGender);

        Organization organization = resolveOrganization(gender);
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

    private Organization resolveOrganization(Gender gender) {
        return gender.equals(Gender.MAN) ? Organization.ATP : Organization.WTA;
    }

    private Gender resolveGender(String gender) {
        return gender.equalsIgnoreCase(Gender.MAN.toString()) ? Gender.MAN : Gender.WOMAN;
    }

    private int promptUserForRating(Organization organization) {
        System.out.print("Enter a " + organization.toString() + " rating: ");
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

    private String promptUserForId() {
        System.out.println("\nChoose player ID: ");
        return scanner.nextLine();
    }

    private void selectPlayersForMatchSimulation() {
        playersDatabase.print();
        if (playersDatabase.getPlayers().isEmpty()) {
            return;
        }

        Player firstPlayer = resolvePlayer(Integer.parseInt(promptUserForId()));
        if (!isValidPlayer(firstPlayer)) {
            return;
        }

        Player secondPlayer = resolvePlayer(Integer.parseInt(promptUserForId()));
        if (!isValidPlayer(secondPlayer)) {
            return;
        }

        match = new Match(firstPlayer, secondPlayer);
        System.out.println("\n" + firstPlayer.getName() + " is playing against "
                + secondPlayer.getName() + "\n");
    }

    private boolean isValidPlayer(Player player) {
        if (player == null) {
            System.out.println("Can`t find player in database. Please choose a valid player ID from the list.");
            return false;
        }
        System.out.println("Selected " + player.getName() + " for a match.");
        return true;
    }

    private Player resolvePlayer(int id) {
        return playersDatabase.findPlayer(id);
    }

    private void simulateMatch() {
        selectPlayersForMatchSimulation();
        if (match == null) {
            print();
            return;
        }
        match.simulate();
        print();
        match = null;
    }
}
