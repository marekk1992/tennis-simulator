package tennissimulator.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        System.out.println("PlayerID | Name | Organization-Rating");
        players
                .stream()
                .sorted()
                .forEach(System.out::println);
    }

    public Optional<Player> findPlayer(int id) {
        return players
                .stream()
                .filter(player -> player.getId().equals(id))
                .findFirst();
    }

}