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
                TieBreak tieBreak = new TieBreak(configuration.getPointsToWinMatchTieBreak(), firstPlayer, secondPlayer);
                result.initializeTieBreak(tieBreak);
                while (tieBreak.hasNoWinner()) {
                    simulateServe();
                }
                result.resetTieBreak();
            } else if (set.hasTieBreak()) {
                TieBreak tieBreak = new TieBreak(configuration.getPointsToWinSetTieBreak(), firstPlayer, secondPlayer);
                result.initializeTieBreak(tieBreak);
                while (tieBreak.hasNoWinner()) {
                    simulateServe();
                }
                result.resetTieBreak();
            } else {
                simulateServe();
            }
        }
        result.showFinalScore();
        firstPlayer.getMatchStats().setDefaultValues();
        secondPlayer.getMatchStats().setDefaultValues();
        set.clearMatchResult();
    }

    private void initialize() {
        serve = new Serve(firstPlayer, secondPlayer);
        configuration = new Configuration(2, 1, 7, 10);
        set = new Set(firstPlayer, secondPlayer, configuration.getGamesInSet());
        result = new Result(firstPlayer, secondPlayer, set, new Game(firstPlayer, secondPlayer), serve);
    }

    private boolean hasNoWinner() {
        return firstPlayer.getMatchStats().getSetsWon() != configuration.getSetsToWinMatch()
                && secondPlayer.getMatchStats().getSetsWon() != configuration.getSetsToWinMatch();
    }

    private boolean hasTieBreak() {
        return firstPlayer.getMatchStats().getSetsWon() == (configuration.getSetsToWinMatch() - 1)
                && secondPlayer.getMatchStats().getSetsWon() == (configuration.getSetsToWinMatch() - 1)
                && firstPlayer.getMatchStats().getGamesWon() == configuration.getGamesInSet()
                && secondPlayer.getMatchStats().getGamesWon() == configuration.getGamesInSet();
    }

    private void simulateServe() {
        result.show();
        result.applySimulationResult(serve.simulate());
        result.update();
        Button.pressKey();
    }
}
