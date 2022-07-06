package tennisSimulator;

public class Game {

    private final Player firstPlayer;
    private final Player secondPlayer;

    public Game(Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

    public void updateStatus(Player firstPlayer, Player secondPlayer) {
        if (firstPlayer.getMatchStats().getGameStatus().equals(Point.FORTY)
                && secondPlayer.getMatchStats().getGameStatus().equals(Point.A)) {
            secondPlayer.getMatchStats().setGameStatus(Point.FORTY);
        } else {
            firstPlayer.getMatchStats().setGameStatus(getNextPoint(firstPlayer.getMatchStats().getGameStatus(),
                    secondPlayer.getMatchStats().getGameStatus()));
        }
    }

    public void resetStatus() {
        firstPlayer.getMatchStats().resetGameStatus();
        secondPlayer.getMatchStats().resetGameStatus();
    }

    private Point getNextPoint(Point firstPlayerPoint, Point secondPlayerPoint){
        switch (firstPlayerPoint) {
            case LOVE:
                return Point.FIFTEEN;
            case FIFTEEN:
                return Point.THIRTY;
            case THIRTY:
                return Point.FORTY;
            case FORTY:
                if (secondPlayerPoint.equals(Point.A)) {
                    return Point.FORTY;
                } else if (secondPlayerPoint.equals(Point.FORTY)) {
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

    public boolean firstPlayerWonGame() {
        return firstPlayer.getMatchStats().getGameStatus().equals(Point.GAME);
    }

    public boolean secondPlayerWonGame() {
        return secondPlayer.getMatchStats().getGameStatus().equals(Point.GAME);
    }
}
