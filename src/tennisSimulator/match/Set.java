package tennisSimulator.match;

import tennisSimulator.config.Configuration;
import tennisSimulator.player.Player;

public class Set {

    private final Player firstPlayer;
    private final Player secondPlayer;
    private final ScoreTable scoreTable;
    private final Game game;
    private final int games;
    private Integer firstPlayerGamesWon;
    private Integer secondPlayerGamesWon;

    public Set(Player firstPlayer, Player secondPlayer, ScoreTable scoreTable) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.scoreTable = scoreTable;
        game = new Game(firstPlayer, secondPlayer, scoreTable);
        this.games = Configuration.games;
        firstPlayerGamesWon = 0;
        secondPlayerGamesWon = 0;
    }

    public void simulate() {
        while (hasNoWinner()) {
            Player gameWinner = game.simulate();
            if (gameWinner.equals(firstPlayer)) {
                updateResult(firstPlayer);
            } else {
                updateResult(secondPlayer);
            }
            scoreTable.updateWithGame(firstPlayerGamesWon.toString(), secondPlayerGamesWon.toString());
            changeServer();
        }
    }

    private void changeServer() {
        if (firstPlayer.isServing()) {
            firstPlayer.setServing(false);
            secondPlayer.setServing(true);
        } else {
            firstPlayer.setServing(true);
            secondPlayer.setServing(false);
        }
    }

    public void resetScore() {
        firstPlayerGamesWon = 0;
        secondPlayerGamesWon = 0;
    }

    private boolean firstPlayerHasWonSet() {
        return (firstPlayerGamesWon >= games && firstPlayerGamesWon - secondPlayerGamesWon > 1)
                || firstPlayerGamesWon == (games + 1);
    }

    private boolean secondPlayerHasWonSet() {
        return (secondPlayerGamesWon >= games && secondPlayerGamesWon - firstPlayerGamesWon > 1)
                || secondPlayerGamesWon == (games + 1);
    }

    public void updateResult(Player player) {
        if (player.equals(firstPlayer)) {
            firstPlayerGamesWon++;
        } else {
            secondPlayerGamesWon++;
        }
    }

    public boolean hasWinner() {
        return firstPlayerHasWonSet() || secondPlayerHasWonSet();
    }

    public boolean hasTieBreak() {
        return firstPlayerGamesWon == games && secondPlayerGamesWon == games;
    }

    private boolean hasNoWinner() {
        return !hasWinner() && !hasTieBreak();
    }

    public Player simulateTieBreak(int pointsToWinTieBreak) {
        Player serverAfterTieBreak = getServerOfFirstGameAfterTieBreak();
        TieBreak tieBreak = new TieBreak(pointsToWinTieBreak, firstPlayer, secondPlayer, scoreTable);
        Player tieBreakWinner = tieBreak.simulate();
        updateResult(tieBreakWinner);
        scoreTable.updateWithGame(firstPlayerGamesWon.toString(), secondPlayerGamesWon.toString());
        setNextServer(serverAfterTieBreak);
        return tieBreakWinner;
    }

    public Player getWinner() {
        return firstPlayerHasWonSet() ? firstPlayer : secondPlayer;
    }

    private Player getServerOfFirstGameAfterTieBreak() {
        return firstPlayer.isServing() ? secondPlayer : firstPlayer;
    }

    private void setNextServer(Player player) {
        if (player.equals(firstPlayer)) {
            firstPlayer.setServing(true);
            secondPlayer.setServing(false);
        } else {
            secondPlayer.setServing(true);
            firstPlayer.setServing(false);
        }
    }
}
