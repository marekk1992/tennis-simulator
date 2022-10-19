package tennissimulator.menu;

import tennissimulator.match.EmptyDatabaseException;
import tennissimulator.match.InvalidPlayerIdException;
import tennissimulator.match.Match;
import tennissimulator.player.Gender;
import tennissimulator.player.Organization;
import tennissimulator.player.Player;
import tennissimulator.player.PlayersDatabase;

import java.util.Optional;
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
        if (playersDatabase.getPlayers().isEmpty()) {
            throw new EmptyDatabaseException("Players database is empty.");
        }
        playersDatabase.print();

        Optional<Player> firstPlayer = playersDatabase.findPlayer(Integer.parseInt(promptUserForId()));
        if (firstPlayer.isEmpty()) {
            throw new InvalidPlayerIdException("Can`t find player in database according to given player ID.");
        }
        Optional<Player> secondPlayer = playersDatabase.findPlayer(Integer.parseInt(promptUserForId()));
        if (secondPlayer.isEmpty()) {
            throw new InvalidPlayerIdException("Can`t find player in database according to given player ID.");
        }

        match = new Match(firstPlayer.get(), secondPlayer.get());
        System.out.println("\n" + firstPlayer.get().getName() + " is playing against "
                + secondPlayer.get().getName() + "\n");
    }

    private void simulateMatch() {
        try {
            selectPlayersForMatchSimulation();
            match.simulate();
            print();
            match = null;
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            print();
        }
    }

}