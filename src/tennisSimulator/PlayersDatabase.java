package tennisSimulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayersDatabase {

    private static final PlayersDatabase instance = new PlayersDatabase();
    private final List<Player> players;

    private PlayersDatabase() {
        players = new ArrayList<>();
    }

    public static PlayersDatabase getInstance() {
        return instance;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        if (players.contains(player)) {
            System.out.println("Player " + player.getName() + " already exists in database.");
        } else if (player == null) {
            System.out.println("Addition failed. Please check your input.");
        } else {
            players.add(player);
            System.out.println("Added " + player.getName() + " to database.");
        }
    }

    public void print() {
        if (players.isEmpty()) {
            System.out.println("Players database is empty.");
        } else {
            Collections.sort(players);
            for (Player player : players) {
                System.out.println(player);
            }
        }
    }

    public Player findPlayer(String name) {
        for (Player player : players) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        return null;
    }
}
