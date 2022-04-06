package tennisSimulator;

public class MatchSimulator {

    private Player firstPlayer;
    private Player secondPlayer;

    public MatchSimulator(Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

    public void setFirstServer() {
        if (Math.random() >= 0.5) {
            firstPlayer.setServing(true);
        } else {
            secondPlayer.setServing(true);
        }
    }

    public void changeServer() {
        if (!firstPlayer.isServing()) {
            firstPlayer.setServing(true);
            secondPlayer.setServing(false);
        } else {
            firstPlayer.setServing(false);
            secondPlayer.setServing(true);
        }
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }
}
