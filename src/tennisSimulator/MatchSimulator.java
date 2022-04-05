package tennisSimulator;

public class MatchSimulator {

    private final Player playerOne;
    private final Player playerTwo;

    public MatchSimulator(MatchParticipants matchParticipants) {
        playerOne = matchParticipants.getParticipants().get(0);
        playerTwo = matchParticipants.getParticipants().get(1);
    }

    public void setFirstServer() {
        if (Math.random() >= 0.5) {
            playerOne.setServing(true);
        } else {
            playerTwo.setServing(true);
        }
    }

    public void changeServer(MatchParticipants matchParticipants) {
        if (!playerOne.isServing()) {
            playerOne.setServing(true);
            playerTwo.setServing(false);
        } else {
            playerOne.setServing(false);
            playerTwo.setServing(true);
        }
    }
}
