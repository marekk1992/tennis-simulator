package tennisSimulator;

public class MatchSimulator {

    private final Player firstPlayer;
    private final Player secondPlayer;

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
        if (firstPlayer.isServing()) {
            firstPlayer.setServing(false);
            secondPlayer.setServing(true);
        } else {
            firstPlayer.setServing(true);
            secondPlayer.setServing(false);
        }
    }

    public void showResult() {
        System.out.println("\t\t  GAME \t SET \tMATCH");
        System.out.println(firstPlayer.getAbbreviatedName() + "  " + firstPlayer.getHandsWon() + "\t   "
                + firstPlayer.getGamesWon() + "\t  " + firstPlayer.getSetsWon());
        System.out.println(secondPlayer.getAbbreviatedName() + "  " + secondPlayer.getHandsWon() + "\t   "
                + secondPlayer.getGamesWon() + "\t  " + secondPlayer.getSetsWon());
    }
}
