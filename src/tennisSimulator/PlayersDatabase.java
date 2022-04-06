package tennisSimulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayersDatabase {

    private List<Player> players;

    public PlayersDatabase() {
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
                System.out.println(player);
            }
        }
    }

    public Player findPlayer(String name) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getName().equals(name)) {
                return players.get(i);
            }
        }
        return null;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
