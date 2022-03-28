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

    public void printDatabase() {
        if (players.isEmpty()) {
            System.out.println("Players database is empty.");
        } else {
            Collections.sort(players);
            int index = 1;
            for (Player player : players) {
                String men = "men";
                String rating = "";
                if (player.getGender().equals(men)) {
                    rating += "ATP";
                } else {
                    rating += "WTA";
                }
                System.out.println(index + ". " + player.getName()
                        + " (" + rating + " - " + player.getRating() + ")");
                index++;
            }
        }
    }

    public void findPlayer(String name) {

    }
}
