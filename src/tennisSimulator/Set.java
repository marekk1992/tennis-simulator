package tennisSimulator;

import java.util.HashMap;
import java.util.Map;

public class Set {

    private final Player firstPlayer;
    private final Player secondPlayer;
    private int setCounter;
    private final int games;
    private final Map<Integer, String> setResults;

    public Set(Player firstPlayer, Player secondPlayer, int games) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        setCounter = 1;
        this.games = games;
        setResults = new HashMap<>();
    }

    public void saveResult(int firstPlayerGamesWon, int secondPLayerGamesWon) {
        String setResult = firstPlayerGamesWon + "-" + secondPLayerGamesWon;
        setResults.put(setCounter, setResult);
        setCounter++;
    }

    public Map<Integer, String> getSetResults() {
        return setResults;
    }

    public void resetResult() {
        secondPlayer.getMatchStats().resetGamesWon();
        firstPlayer.getMatchStats().resetGamesWon();
    }

    public boolean hasTieBreak() {
        return firstPlayer.getMatchStats().getGamesWon() == games
                && secondPlayer.getMatchStats().getGamesWon() == games;
    }

    public Player getPlayerWhichWonSet() {
        if (firstPlayerHasWonSet()) {
            return firstPlayer;
        } else if (secondPlayerHasWonSet()) {
            return secondPlayer;
        }
        return null;
    }

    private boolean firstPlayerHasWonSet() {
        return (firstPlayer.getMatchStats().getGamesWon() >= games
                && firstPlayer.getMatchStats().getGamesWon() - secondPlayer.getMatchStats().getGamesWon() > 1)
                || firstPlayer.getMatchStats().getGamesWon() == (games + 1);
    }

    private boolean secondPlayerHasWonSet() {
        return (secondPlayer.getMatchStats().getGamesWon() >= games
                && secondPlayer.getMatchStats().getGamesWon() - firstPlayer.getMatchStats().getGamesWon() > 1)
                || secondPlayer.getMatchStats().getGamesWon() == (games + 1);
    }

    public void updateResult(Player player) {
        player.getMatchStats().setGamesWon(player.getMatchStats().getGamesWon() + 1);
    }

    public void clearMatchResult() {
        setResults.clear();
    }
}
