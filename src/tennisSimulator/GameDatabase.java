package tennisSimulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameDatabase {

    private List<Player> players;

    public GameDatabase() {
        players = new ArrayList<>();
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
                player.toString();
            }
        }
    }
}
