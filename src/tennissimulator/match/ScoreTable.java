package tennissimulator.match;

import tennissimulator.player.Player;

public class ScoreTable {

    private static final int SETS_TO_WIN_MATCH = 3;

    private final Player firstPlayer;
    private final Player secondPlayer;
    private String firstPlayerPoints;
    private String secondPlayerPoints;
    private String firstPlayerServingStatus;
    private String secondPlayerServingStatus;
    private String matchPhase = " ";
    private String firstPlayerScoreDisplay;
    private String secondPlayerScoreDisplay;
    private int firstPlayerGamesWon;
    private int secondPlayerGamesWon;
    private int firstPlayerSetsWon;
    private int secondPlayerSetsWon;

    public ScoreTable(Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        initialize();
    }

    private void initialize() {
        matchPhase = " ";
        firstPlayerPoints = "";
        secondPlayerPoints = "";
        firstPlayerScoreDisplay = "";
        secondPlayerScoreDisplay = "";
        firstPlayerGamesWon = 0;
        secondPlayerGamesWon = 0;
        firstPlayerSetsWon = 0;
        secondPlayerSetsWon = 0;
    }

    public void display() {
        setServingStatus();
        showScoreTable();
    }

    public void setServingStatus() {
        if (firstPlayer.isServing()) {
            firstPlayerServingStatus = "SERV";
            secondPlayerServingStatus = "    ";
        } else {
            firstPlayerServingStatus = "    ";
            secondPlayerServingStatus = "SERV";
        }
    }

    private void showScoreTable() {
        System.out.println("\t\t    " + matchPhase + "     S" + getPossibleNumberOfSets());
        System.out.println(firstPlayer.getAbbreviatedName() + "   " + firstPlayerServingStatus + "\t"
                + firstPlayerPoints + "\t  " + firstPlayerSetsWon + "\t   "
                + firstPlayerScoreDisplay + firstPlayerGamesWon);
        System.out.println(secondPlayer.getAbbreviatedName() + "   " + secondPlayerServingStatus + "\t"
                + secondPlayerPoints + "\t  " + secondPlayerSetsWon + "\t   "
                + secondPlayerScoreDisplay + secondPlayerGamesWon);
    }

    public void showFinalScore() {
        System.out.println("MATCH IS FINISHED. MATCH RESULT: ");
        System.out.println(firstPlayer.getName() + "   [" + firstPlayerSetsWon + " - "
                + secondPlayerSetsWon + "]   " + secondPlayer.getName());
        System.out.println(" " + firstPlayer.getOrganization() + ": " + firstPlayer.getRating() +
                "\t\t\t\t   " + secondPlayer.getOrganization() + ": " + secondPlayer.getRating() + "\n");
        System.out.println("\t   S" + getPossibleNumberOfSets());
        System.out.println(firstPlayer.getAbbreviatedName() + "\t   " + firstPlayerSetsWon + "    "
                + firstPlayerScoreDisplay);
        System.out.println(secondPlayer.getAbbreviatedName() + "\t   " + secondPlayerSetsWon + "    "
                + secondPlayerScoreDisplay + "\n");
    }

    public void updateWithServe(String firstPlayerPoints, String secondPlayerPoints) {
        this.firstPlayerPoints = firstPlayerPoints;
        this.secondPlayerPoints = secondPlayerPoints;
    }

    public void updateWithGame(int firstPlayerGamesWon, int secondPlayerGamesWon) {
        this.firstPlayerGamesWon = firstPlayerGamesWon;
        this.secondPlayerGamesWon = secondPlayerGamesWon;
    }

    public void updateWithSet(int firstPlayerSetsWon, int secondPlayerSetsWon) {
        this.firstPlayerSetsWon = firstPlayerSetsWon;
        this.secondPlayerSetsWon = secondPlayerSetsWon;
        firstPlayerScoreDisplay += firstPlayerGamesWon + "    ";
        secondPlayerScoreDisplay += secondPlayerGamesWon + "    ";
        firstPlayerGamesWon = 0;
        secondPlayerGamesWon = 0;
    }

    public void initializeTieBreak() {
        matchPhase = "T";
    }

    public void clearTieBreak() {
        matchPhase = " ";
    }

    private String getPossibleNumberOfSets() {
        StringBuilder setNumbers = new StringBuilder("    ");
        for (int i = 1; i <= ((SETS_TO_WIN_MATCH - 1) * 2) + 1; i++) {
            setNumbers.append(i).append("    ");
        }

        return setNumbers.toString();
    }

}