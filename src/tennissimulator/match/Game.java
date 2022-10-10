package tennissimulator.match;

import tennissimulator.menu.Button;
import tennissimulator.player.Player;

public class Game {

    private final Player firstPlayer;
    private final Player secondPlayer;
    private final ScoreTable scoreTable;
    private final Serve serve;
    private Point firstPlayerGameStatus;
    private Point secondPlayerGameStatus;

    public Game(Player firstPlayer, Player secondPlayer, ScoreTable scoreTable) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.scoreTable = scoreTable;
        serve = new Serve(firstPlayer, secondPlayer);
        firstPlayerGameStatus = Point.LOVE;
        secondPlayerGameStatus = Point.LOVE;
    }

    public Player simulate() {
        while (hasNoWinner()) {
            simulateServe();
        }
        Player winner = getWinner();
        resetStatus();

        return winner;
    }

    private void updateStatus(Player serveWinner) {
        if (serveWinner.equals(firstPlayer)) {
            updateStatusWhenFirstPlayerWon();
        } else {
            updateStatusWhenSecondPlayerWon();
        }
    }

    private void updateStatusWhenFirstPlayerWon() {
        if (serveLoserHasAdvantage(secondPlayerGameStatus)) {
            secondPlayerGameStatus = Point.FORTY;
        } else {
            firstPlayerGameStatus = getNextPoint(firstPlayerGameStatus, secondPlayerGameStatus);
        }
    }

    private void updateStatusWhenSecondPlayerWon() {
        if (serveLoserHasAdvantage(firstPlayerGameStatus)) {
            firstPlayerGameStatus = Point.FORTY;
        } else {
            secondPlayerGameStatus = getNextPoint(secondPlayerGameStatus, firstPlayerGameStatus);
        }
    }

    private void resetStatus() {
        firstPlayerGameStatus = Point.LOVE;
        secondPlayerGameStatus = Point.LOVE;
        scoreTable.updateWithServe(firstPlayerGameStatus.toString(), secondPlayerGameStatus.toString());
    }

    private Point getNextPoint(Point serveWinnerGameStatus, Point serveLoserGameStatus) {
        switch (serveWinnerGameStatus) {
            case LOVE:
                return Point.FIFTEEN;
            case FIFTEEN:
                return Point.THIRTY;
            case THIRTY:
                return Point.FORTY;
            case FORTY:
                if (serveLoserGameStatus.equals(Point.A)) {
                    return Point.FORTY;
                } else if (serveLoserGameStatus.equals(Point.FORTY)) {
                    return Point.A;
                } else {
                    return Point.GAME;
                }
            case A:
                return Point.GAME;
            default:
                throw new IllegalStateException("Case not found - This line should be unreachable");
        }
    }

    private boolean hasNoWinner() {
        return !firstPlayerWonGame() && !secondPlayerWonGame();
    }

    private boolean firstPlayerWonGame() {
        return firstPlayerGameStatus.equals(Point.GAME);
    }

    private boolean secondPlayerWonGame() {
        return secondPlayerGameStatus.equals(Point.GAME);
    }

    private boolean serveLoserHasAdvantage(Point serveLoserGameStatus) {
        return serveLoserGameStatus.equals(Point.A);
    }

    private void simulateServe() {
        scoreTable.updateWithServe(firstPlayerGameStatus.toString(), secondPlayerGameStatus.toString());
        scoreTable.display();
        Button.pressKey();
        Player serveWinner = serve.simulate();
        updateStatus(serveWinner);
    }

    private Player getWinner() {
        return firstPlayerWonGame() ? firstPlayer : secondPlayer;
    }

}