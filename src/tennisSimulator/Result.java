package tennisSimulator;

public class Result {

    private final Player firstPlayer;
    private final Player secondPlayer;
    private final Set set;
    private final Game game;
    private final Serve serve;
    private TieBreak tieBreak;

    public Result(Player firstPlayer, Player secondPlayer, Set set, Game game, Serve serve) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.set = set;
        this.game = game;
        this.serve = serve;
    }

    public void show() {
        displayServer();
        if (tieBreak != null) {
            displayTieBreakScoreTable();
        } else {
            displayScoreTable();
        }
    }

    private void displayServer() {
        if (firstPlayer.getMatchStats().isServing()) {
            System.out.println(firstPlayer.getName() + " is serving");
        } else {
            System.out.println(secondPlayer.getName() + " is serving");
        }
    }

    private void displayScoreTable() {
        System.out.println("\t\t  GAME \t SET");
        System.out.println(firstPlayer.getAbbreviatedName() + "  " + firstPlayer.getMatchStats().getGameStatus()
                + "\t   " + firstPlayer.getMatchStats().getGamesWon() + "\t  " + firstPlayer.getMatchStats().getSetsWon());
        System.out.println(secondPlayer.getAbbreviatedName() + "  " + secondPlayer.getMatchStats().getGameStatus()
                + "\t   " + secondPlayer.getMatchStats().getGamesWon() + "\t  " + secondPlayer.getMatchStats().getSetsWon());
    }

    private void displayTieBreakScoreTable() {
        System.out.println("\t T   GAME \t SET");
        System.out.println(firstPlayer.getAbbreviatedName() + "  " + firstPlayer.getMatchStats().getTieBreakPointsWon()
                + "\t   " + firstPlayer.getMatchStats().getGamesWon() + "\t  " + firstPlayer.getMatchStats().getSetsWon());
        System.out.println(secondPlayer.getAbbreviatedName() + "  " + secondPlayer.getMatchStats().getTieBreakPointsWon()
                + "\t   " + secondPlayer.getMatchStats().getGamesWon() + "\t  " + secondPlayer.getMatchStats().getSetsWon());
    }

    public void showFinalScore() {
        System.out.println("Match is finished. Match result: ");
        printEachSetResult();
        System.out.println("\n" + "[" + firstPlayer.getAttributes().getRating() + "]" + firstPlayer.getName() + " "
                + firstPlayer.getMatchStats().getSetsWon() + " - " + secondPlayer.getMatchStats().getSetsWon() + " "
                + secondPlayer.getName() + "[" + secondPlayer.getAttributes().getRating() + "]\n");
    }

    public void applySimulationResult(Player playerWhichWonServe) {
        if (tieBreak != null) {
            if (firstPlayer.equals(playerWhichWonServe)) {
                firstPlayer.getMatchStats().setTieBreakPointsWon(firstPlayer.getMatchStats().getTieBreakPointsWon() + 1);
            } else {
                secondPlayer.getMatchStats().setTieBreakPointsWon(secondPlayer.getMatchStats().getTieBreakPointsWon() + 1);
            }
            tieBreak.setServesPlayed(tieBreak.getServesPlayed() + 1);
        } else {
            if (firstPlayer.equals(playerWhichWonServe)) {
                game.updateStatus(firstPlayer, secondPlayer);
            } else {
                game.updateStatus(secondPlayer, firstPlayer);
            }
        }
    }

    public void update() {
        if (tieBreak != null) {
            if (tieBreak.hasNoWinner() && (tieBreak.getServesPlayed() % 2) != 0) {
                serve.changeServer();
            }
        }
        if (game.firstPlayerWonGame() || (tieBreak != null && tieBreak.firstPlayerHasWonTieBreak())) {
            updateMatchStatus(firstPlayer, secondPlayer);
        } else if (game.secondPlayerWonGame() || (tieBreak != null && tieBreak.secondPlayerHasWonTieBreak())) {
            updateMatchStatus(secondPlayer, firstPlayer);
        }
    }

    private void printEachSetResult() {
        System.out.print("\t");
        for (String result : set.getSetResults().values()) {
            System.out.print(result + " ");
        }
    }

    private void incrementNumberOfWonSets(Player player) {
        player.getMatchStats().setSetsWon(player.getMatchStats().getSetsWon() + 1);
    }

    public void initializeTieBreak(TieBreak tieBreak) {
        this.tieBreak = tieBreak;
    }

    public void resetTieBreak() {
        tieBreak = null;
    }

    private void updateMatchStatus(Player player1, Player player2) {
        game.resetStatus();
        set.updateResult(player1);
        serve.changeServer();
        if (player1.equals(set.getPlayerWhichWonSet())) {
            incrementNumberOfWonSets(player1);
            set.saveResult(player1.getMatchStats().getGamesWon(), player2.getMatchStats().getGamesWon());
            set.resetResult();
        } else if (player2.equals(set.getPlayerWhichWonSet())) {
            incrementNumberOfWonSets(player2);
            set.saveResult(player2.getMatchStats().getGamesWon(), player1.getMatchStats().getGamesWon());
            set.resetResult();
        }
    }
}
