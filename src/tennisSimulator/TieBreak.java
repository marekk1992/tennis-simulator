package tennisSimulator;

public class TieBreak {

    private final int pointsToWinTieBreak;
    private int servesPlayed;
    private final Player firstPlayer;
    private final Player secondPlayer;

    public TieBreak(int pointsToWinTieBreak, Player firstPlayer, Player secondPlayer) {
        this.pointsToWinTieBreak = pointsToWinTieBreak;
        servesPlayed = 0;
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

    public int getServesPlayed() {
        return servesPlayed;
    }

    public void setServesPlayed(int servesPlayed) {
        this.servesPlayed = servesPlayed;
    }

    public int getPointsToWinTieBreak() {
        return pointsToWinTieBreak;
    }

    public boolean hasNoWinner() {
        return !firstPlayerHasWonTieBreak() && !secondPlayerHasWonTieBreak();
    }

    public boolean firstPlayerHasWonTieBreak() {
        return firstPlayer.getMatchStats().getTieBreakPointsWon() >= pointsToWinTieBreak
                && (firstPlayer.getMatchStats().getTieBreakPointsWon()
                - secondPlayer.getMatchStats().getTieBreakPointsWon()) > 1;
    }

    public boolean secondPlayerHasWonTieBreak() {
        return secondPlayer.getMatchStats().getTieBreakPointsWon() >= pointsToWinTieBreak
                && (secondPlayer.getMatchStats().getTieBreakPointsWon()
                - firstPlayer.getMatchStats().getTieBreakPointsWon()) > 1;
    }
}
