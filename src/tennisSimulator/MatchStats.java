package tennisSimulator;

public class MatchStats {

    private boolean isServing;
    private int gamesWon;
    private int setsWon;
    private Point gameStatus;
    private int tieBreakPointsWon;

    public MatchStats() {
        setDefaultValues();
    }

    public boolean isServing() {
        return isServing;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public int getSetsWon() {
        return setsWon;
    }

    public Point getGameStatus() {
        return gameStatus;
    }

    public void setServing(boolean serving) {
        isServing = serving;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public void setSetsWon(int setsWon) {
        this.setsWon = setsWon;
    }

    public void setGameStatus(Point gameStatus) {
        this.gameStatus = gameStatus;
    }

    public void setDefaultValues() {
        isServing = false;
        gamesWon = 0;
        setsWon = 0;
        gameStatus = Point.LOVE;
        tieBreakPointsWon = 0;
    }

    public void resetGameStatus() {
        gameStatus = Point.LOVE;
    }

    public void resetGamesWon() {
        gamesWon = 0;
    }

    public int getTieBreakPointsWon() {
        return tieBreakPointsWon;
    }

    public void setTieBreakPointsWon(int tieBreakPointsWon) {
        this.tieBreakPointsWon = tieBreakPointsWon;
    }
}
