package tennissimulator.match;

import tennissimulator.player.Player;

import java.util.Optional;

public class Match {

    private static final int SETS_TO_WIN_MATCH = 3;
    private static final int POINTS_TO_WIN_SET_TIEBREAK = 7;
    private static final int POINTS_TO_WIN_MATCH_TIEBREAK = 10;

    private final Player firstPlayer;
    private final Player secondPlayer;
    private ScoreTable scoreTable;
    private Set set;
    private int firstPlayerSetsWon;
    private int secondPlayerSetsWon;

    public Match(Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        initialize();
    }

    private void initialize() {
        scoreTable = new ScoreTable(firstPlayer, secondPlayer);
        set = new Set(firstPlayer, secondPlayer, scoreTable);
        firstPlayerSetsWon = 0;
        secondPlayerSetsWon = 0;
    }

    public void simulate() {
        defineFirstServer();
        while (hasNoWinner()) {
            Optional<Player> setWinner = set.simulate();
            if (setWinner.isPresent()) {
                updateResult(setWinner.get());
            } else {
                updateResult(set.simulateTieBreak(getPointsToWinTieBreak()));
            }
        }

        scoreTable.showFinalScore();
        resetScore();
    }

    private void updateResult(Player player) {
        updateScore(player);
        scoreTable.updateWithSet(firstPlayerSetsWon, secondPlayerSetsWon);
        set.resetScore();
    }

    private void defineFirstServer() {
        if (Math.random() >= 0.5) {
            firstPlayer.setServing(true);
        } else {
            secondPlayer.setServing(true);
        }
    }

    private boolean hasNoWinner() {
        return firstPlayerSetsWon != SETS_TO_WIN_MATCH && secondPlayerSetsWon != SETS_TO_WIN_MATCH;
    }

    private void resetScore() {
        firstPlayerSetsWon = 0;
        secondPlayerSetsWon = 0;
    }

    private void updateScore(Player setWinner) {
        if (setWinner.equals(firstPlayer)) {
            firstPlayerSetsWon++;
        } else {
            secondPlayerSetsWon++;
        }
    }

    private int getPointsToWinTieBreak() {
        return firstPlayerSetsWon == SETS_TO_WIN_MATCH - 1 && secondPlayerSetsWon == SETS_TO_WIN_MATCH - 1 ?
                POINTS_TO_WIN_MATCH_TIEBREAK : POINTS_TO_WIN_SET_TIEBREAK;
    }

}