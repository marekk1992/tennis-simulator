package tennissimulator.match;

import tennissimulator.menu.Button;
import tennissimulator.player.Player;

public class Tiebreak {

    private final int pointsToWinTieBreak;
    private int servesPlayed;
    private final Player firstPlayer;
    private final Player secondPlayer;
    private final ScoreTable scoreTable;
    private final Serve serve;
    private Integer firstPlayerTieBreakPointsWon;
    private Integer secondPlayerTieBreakPointsWon;

    public Tiebreak(int pointsToWinTieBreak, Player firstPlayer, Player secondPlayer, ScoreTable scoreTable) {
        this.pointsToWinTieBreak = pointsToWinTieBreak;
        servesPlayed = 0;
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.scoreTable = scoreTable;
        serve = new Serve(firstPlayer, secondPlayer);
        firstPlayerTieBreakPointsWon = 0;
        secondPlayerTieBreakPointsWon = 0;
    }

    public Player simulate() {
        while (hasNoWinner()) {
            scoreTable.display();
            Button.pressKey();
            Player serveWinner = serve.simulate();
            updateResult(serveWinner);
            scoreTable.updateWithServe(firstPlayerTieBreakPointsWon.toString(), secondPlayerTieBreakPointsWon.toString());
            if (hasNoWinner() && servesPlayed % 2 != 0) {
                changeServer();
            }
        }

        Player tieBreakWinner = getWinner();
        resetScore();
        scoreTable.clearTieBreak();
        scoreTable.updateWithServe(firstPlayerTieBreakPointsWon.toString(), secondPlayerTieBreakPointsWon.toString());

        return tieBreakWinner;
    }

    private void changeServer() {
        firstPlayer.changeServer();
        secondPlayer.changeServer();
    }

    public boolean hasNoWinner() {
        return !firstPlayerHasWonTieBreak() && !secondPlayerHasWonTieBreak();
    }

    private boolean firstPlayerHasWonTieBreak() {
        return firstPlayerTieBreakPointsWon >= pointsToWinTieBreak
                && (firstPlayerTieBreakPointsWon - secondPlayerTieBreakPointsWon) > 1;
    }

    private boolean secondPlayerHasWonTieBreak() {
        return secondPlayerTieBreakPointsWon >= pointsToWinTieBreak
                && (secondPlayerTieBreakPointsWon - firstPlayerTieBreakPointsWon) > 1;
    }

    private void updateResult(Player player) {
        if (player.equals(firstPlayer)) {
            firstPlayerTieBreakPointsWon++;
        } else {
            secondPlayerTieBreakPointsWon++;
        }

        servesPlayed++;
    }

    private Player getWinner() {
        return firstPlayerHasWonTieBreak() ? firstPlayer : secondPlayer;
    }

    private void resetScore() {
        servesPlayed = 0;
        firstPlayerTieBreakPointsWon = 0;
        secondPlayerTieBreakPointsWon = 0;
    }

}