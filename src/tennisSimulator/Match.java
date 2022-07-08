package tennisSimulator;

public class Match {

    private final Player firstPlayer;
    private final Player secondPlayer;
    private Set set;
    private Serve serve;
    private Result result;
    private Configuration configuration;


    public Match(Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        initialize();
    }

    public void simulate() {
        serve.defineFirstServer();
        while (hasNoWinner()) {
            if (hasTieBreak()) {
                simulateTieBreak(configuration.getPointsToWinSetTieBreak());
            } else if (set.hasTieBreak()) {
                simulateTieBreak(configuration.getPointsToWinMatchTieBreak());
            } else {
                simulateServe();
            }
        }
        result.showFinalScore();
        resetMatchStats();
        set.clearMatchResult();
    }

    private void initialize() {
        serve = new Serve(firstPlayer, secondPlayer);
        configuration = new Configuration(2, 1, 7, 10);
        set = new Set(firstPlayer, secondPlayer, configuration.getGames());
        result = new Result(firstPlayer, secondPlayer, set, new Game(firstPlayer, secondPlayer), serve);
    }

    private boolean hasNoWinner() {
        return firstPlayer.getMatchStats().getSetsWon() != configuration.getSetsToWinMatch()
                && secondPlayer.getMatchStats().getSetsWon() != configuration.getSetsToWinMatch();
    }

    private boolean hasTieBreak() {
        return firstPlayer.getMatchStats().getSetsWon() == (configuration.getSetsToWinMatch() - 1)
                && secondPlayer.getMatchStats().getSetsWon() == (configuration.getSetsToWinMatch() - 1)
                && firstPlayer.getMatchStats().getGamesWon() == configuration.getGames()
                && secondPlayer.getMatchStats().getGamesWon() == configuration.getGames();
    }

    private void simulateServe() {
        result.show();
        result.applySimulationResult(serve.simulate());
        result.update();
        Button.pressKey();
    }

    private void simulateTieBreak(int points) {
        TieBreak tieBreak = new TieBreak(points, firstPlayer, secondPlayer);
        result.initializeTieBreak(tieBreak);
        while (tieBreak.hasNoWinner()) {
            simulateServe();
        }
        result.resetTieBreak();
    }

    private void resetMatchStats() {
        firstPlayer.getMatchStats().setDefaultValues();
        secondPlayer.getMatchStats().setDefaultValues();
    }
}
