package tennisSimulator;

public class Serve {

    private final Player firstPlayer;
    private final Player secondPlayer;
    private final Double SERVING_FACTOR = 0.2;
    private final Integer RATING_DIFFERENCE;

    public Serve(Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        RATING_DIFFERENCE = firstPlayer.getAttributes().getRating() - secondPlayer.getAttributes().getRating();
    }

    public void defineFirstServer() {
        if (Math.random() >= 0.5) {
            firstPlayer.getMatchStats().setServing(true);
        } else {
            secondPlayer.getMatchStats().setServing(true);
        }
    }

    public void changeServer() {
        if (firstPlayer.getMatchStats().isServing()) {
            firstPlayer.getMatchStats().setServing(false);
            secondPlayer.getMatchStats().setServing(true);
        } else {
            firstPlayer.getMatchStats().setServing(true);
            secondPlayer.getMatchStats().setServing(false);
        }
    }

    public Player simulate() {
        double servingFactor = SERVING_FACTOR;
        if (firstPlayer.getMatchStats().isServing()) {
            servingFactor = -servingFactor;
        }
        double probability = 1 / (1 + Math.pow(10, ((RATING_DIFFERENCE / 150.0) * 0.8 + servingFactor)));
        if (Math.random() < probability) {
            return firstPlayer;
        } else {
            return secondPlayer;
        }
    }
}
