package tennisSimulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Database {

    private List<Player> players;

    public Database() {
        players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        if (player == null) {
            System.out.println("Addition failed. Please provide a valid player.");
        } else if (players.contains(player)) {
            System.out.println("Addition failed. Player " + player.getName()
                    + " already exists in database.");
        } else {
            players.add(player);
            System.out.println(player.getName() + " added to database.");
        }
    }

    public void printDatabase() {
        if (players.isEmpty()) {
            System.out.println("Players database is empty.");
        } else {
            Collections.sort(players);
            int index = 1;
            for (Player player : players) {
                System.out.println(index + ". " + player.getName()
                        + " (" + player.getAtpRating() + " - ATP)");
                index++;
            }
        }
    }
}
