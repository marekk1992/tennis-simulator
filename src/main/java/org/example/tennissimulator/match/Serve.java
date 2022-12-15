package org.example.tennissimulator.match;

import org.example.tennissimulator.player.Player;

public class Serve {

    private final Player firstPlayer;
    private final Player secondPlayer;
    private static final double SERVING_FACTOR = 0.2;
    private final int ratingDifference;

    public Serve(Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        ratingDifference = firstPlayer.getRating() - secondPlayer.getRating();
    }

    public Player simulate() {
        double servingFactor = SERVING_FACTOR;
        if (firstPlayer.isServing()) {
            servingFactor = -SERVING_FACTOR;
        }
        double probability = 1 / (1 + Math.pow(10, ((ratingDifference / 150.0) * 0.8 + servingFactor)));

        return Math.random() < probability ? firstPlayer : secondPlayer;
    }

}
