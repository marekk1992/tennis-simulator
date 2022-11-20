package org.example.tennissimulator.match;

import org.example.tennissimulator.player.Player;

import java.util.Optional;

public class Set {

    private static final int GAMES_TO_WIN_SET = 3;

    private final Player firstPlayer;
    private final Player secondPlayer;
    private final ScoreTable scoreTable;
    private final Game game;
    private Integer firstPlayerGamesWon;
    private Integer secondPlayerGamesWon;

    public Set(Player firstPlayer, Player secondPlayer, ScoreTable scoreTable) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.scoreTable = scoreTable;
        game = new Game(firstPlayer, secondPlayer, scoreTable);
        firstPlayerGamesWon = 0;
        secondPlayerGamesWon = 0;
    }

    public Optional<Player> simulate() {
        while (hasNoWinner()) {
            updateResult(game.simulate());
            scoreTable.updateWithGame(firstPlayerGamesWon, secondPlayerGamesWon);
            changeServer();
        }

        return getWinner();
    }

    private void changeServer() {
        firstPlayer.changeServer();
        secondPlayer.changeServer();
    }

    public void resetScore() {
        firstPlayerGamesWon = 0;
        secondPlayerGamesWon = 0;
    }

    private boolean firstPlayerHasWonSet() {
        return (firstPlayerGamesWon >= GAMES_TO_WIN_SET && isMultiplePointsDifference(firstPlayerGamesWon, secondPlayerGamesWon))
                || firstPlayerGamesWon == (GAMES_TO_WIN_SET + 1);
    }

    private boolean secondPlayerHasWonSet() {
        return (secondPlayerGamesWon >= GAMES_TO_WIN_SET && isMultiplePointsDifference(secondPlayerGamesWon, firstPlayerGamesWon))
                || secondPlayerGamesWon == (GAMES_TO_WIN_SET + 1);
    }

    private boolean isMultiplePointsDifference(int firstPlayerGamesWon, int secondPlayerGamesWon) {
        return firstPlayerGamesWon - secondPlayerGamesWon > 1;
    }

    private void updateResult(Player player) {
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
        return firstPlayerGamesWon == GAMES_TO_WIN_SET && secondPlayerGamesWon == GAMES_TO_WIN_SET;
    }

    private boolean hasNoWinner() {
        return !hasWinner() && !hasTieBreak();
    }

    public Player simulateTieBreak(int pointsToWinTieBreak) {
        scoreTable.initializeTieBreak();
        Player serverAfterTieBreak = getServerOfFirstGameAfterTieBreak();
        Tiebreak tieBreak = new Tiebreak(pointsToWinTieBreak, firstPlayer, secondPlayer, scoreTable);
        Player tieBreakWinner = tieBreak.simulate();
        updateResult(tieBreakWinner);
        scoreTable.updateWithGame(firstPlayerGamesWon, secondPlayerGamesWon);
        setNextServer(serverAfterTieBreak);

        return tieBreakWinner;
    }

    public Optional<Player> getWinner() {
        if (hasWinner()) {
            return firstPlayerHasWonSet() ? Optional.of(firstPlayer) : Optional.of(secondPlayer);
        }

        return Optional.empty();
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