package tennisSimulator.match;

import tennisSimulator.config.Configuration;
import tennisSimulator.player.Player;

public class Match {

    private final Player firstPlayer;
    private final Player secondPlayer;
    private ScoreTable scoreTable;
    private int sets;
    private Set set;
    private int pointsInSetTieBreak;
    private int pointsInMatchTieBreak;
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
        sets = Configuration.sets;
        pointsInSetTieBreak = Configuration.pointsInSetTieBreak;
        pointsInMatchTieBreak = Configuration.pointsInMatchTieBreak;
        firstPlayerSetsWon = 0;
        secondPlayerSetsWon = 0;
    }

    public void simulate() {
        defineFirstServer();
        while (hasNoWinner()) {
            set.simulate();
            Player setWinner = resolveSetWinner();
            if (setWinner != null) {
                updateScore(setWinner);
                scoreTable.updateWithSet(firstPlayerSetsWon, secondPlayerSetsWon);
                set.resetScore();
            }
        }
        scoreTable.showFinalScore();
        resetScore();
    }

    private void defineFirstServer() {
        if (Math.random() >= 0.5) {
            firstPlayer.setServing(true);
        } else {
            secondPlayer.setServing(true);
        }
    }

    private boolean hasNoWinner() {
        return firstPlayerSetsWon != sets && secondPlayerSetsWon != sets;
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

    private Player resolveSetWinner() {
        Player setWinner = null;
        if (set.hasTieBreak()) {
            scoreTable.initializeTieBreak();
            setWinner = set.simulateTieBreak(getPointsToWinTieBreak());
        } else if (set.hasWinner()) {
            setWinner = set.getWinner();
        }
        return setWinner;
    }

    private int getPointsToWinTieBreak() {
        return firstPlayerSetsWon == sets - 1 && secondPlayerSetsWon == sets - 1
                ? pointsInMatchTieBreak : pointsInSetTieBreak;
    }
}

