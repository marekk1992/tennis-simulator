package tennisSimulator;

public class Configuration {

    private final int gamesInSet;
    private final int setsToWinMatch;
    private final int pointsToWinSetTieBreak;
    private final int pointsToWinMatchTieBreak;

    public Configuration(int gamesInSet, int setsToWinMatch, int pointsToWinSetTieBreak, int pointsToWinMatchTieBreak) {
        this.gamesInSet = gamesInSet;
        this.setsToWinMatch = setsToWinMatch;
        this.pointsToWinSetTieBreak = pointsToWinSetTieBreak;
        this.pointsToWinMatchTieBreak = pointsToWinMatchTieBreak;
    }

    public int getGamesInSet() {
        return gamesInSet;
    }

    public int getSetsToWinMatch() {
        return setsToWinMatch;
    }

    public int getPointsToWinSetTieBreak() {
        return pointsToWinSetTieBreak;
    }

    public int getPointsToWinMatchTieBreak() {
        return pointsToWinMatchTieBreak;
    }
}
