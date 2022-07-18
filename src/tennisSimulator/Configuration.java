package tennisSimulator;

public class Configuration {

    private final int games;
    private final int setsToWinMatch;
    private final int pointsToWinSetTieBreak;
    private final int pointsToWinMatchTieBreak;

    public Configuration(int games, int setsToWinMatch, int pointsToWinSetTieBreak, int pointsToWinMatchTieBreak) {
        this.games = games;
        this.setsToWinMatch = setsToWinMatch;
        this.pointsToWinSetTieBreak = pointsToWinSetTieBreak;
        this.pointsToWinMatchTieBreak = pointsToWinMatchTieBreak;
    }

    public int getGames() {
        return games;
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
