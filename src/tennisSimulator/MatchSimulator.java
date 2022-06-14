package tennisSimulator;

import java.util.HashMap;
import java.util.Map;

public class MatchSimulator {

    private final Player firstPlayer;
    private final Player secondPlayer;
    private int setCounter = 0;
    private final Map<Integer, String> setResults;


    public MatchSimulator(Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        setResults = new HashMap<>();
    }

    public void setFirstServer() {
        if (Math.random() >= 0.5) {
            firstPlayer.setServing(true);
        } else {
            secondPlayer.setServing(true);
        }
    }

    public void changeServer() {
        if (firstPlayer.isServing()) {
            firstPlayer.setServing(false);
            secondPlayer.setServing(true);
        } else {
            firstPlayer.setServing(true);
            secondPlayer.setServing(false);
        }
    }

    public void showResult() {
        if (noWinner()) {
            if (firstPlayer.isServing()) {
                System.out.println(firstPlayer.getName() + " is serving");
            } else {
                System.out.println(secondPlayer.getName() + " is serving");
            }
            System.out.println("\t\t  GAME \t SET");
            System.out.println(firstPlayer.getAbbreviatedName() + "  " + convertHandsScore(firstPlayer.getHandsWon())
                    + "\t   " + firstPlayer.getGamesWon() + "\t  " + firstPlayer.getSetsWon());
            System.out.println(secondPlayer.getAbbreviatedName() + "  " + convertHandsScore(secondPlayer.getHandsWon())
                    + "\t   " + secondPlayer.getGamesWon() + "\t  " + secondPlayer.getSetsWon());
        } else {
            System.out.println("Match is finished. Match result: ");
            System.out.print("\t");
            for (String result : setResults.values()) {
                System.out.print(result + " ");
            }
            System.out.println("\n"+ "[" + firstPlayer.getRating() + "]" + firstPlayer.getName() + " "
                    + firstPlayer.getSetsWon() + " - " + secondPlayer.getSetsWon() + " " + secondPlayer.getName()
                    + "[" + secondPlayer.getRating() + "]\n");
            resetValues();
        }
    }

    public void simulateHand() {
        double ratingDifference = firstPlayer.getRating() - secondPlayer.getRating();
        double servingFactor = 0.2;
        if (firstPlayer.isServing()) {
            servingFactor = -0.2;
        }
        double probability = 1 / (1 + Math.pow(10, ((ratingDifference / 150) * 0.8 + servingFactor)));
        applySimulationResult(probability);
    }

    public void updateResult() {
        if (firstPlayer.getHandsWon() == 4 && secondPlayer.getHandsWon() < 3 || firstPlayer.getHandsWon() == 5) {
            changeServer();
            firstPlayer.setHandsWon(0);
            secondPlayer.setHandsWon(0);
            firstPlayer.setGamesWon(firstPlayer.getGamesWon() + 1);
            if (firstPlayer.getGamesWon() > 1 && (firstPlayer.getGamesWon() - secondPlayer.getGamesWon()) > 1) {
                firstPlayer.setSetsWon(firstPlayer.getSetsWon() + 1);
                setCounter++;
                saveSetResult();
                firstPlayer.setGamesWon(0);
                secondPlayer.setGamesWon(0);
            }
        } else if (secondPlayer.getHandsWon() == 4 && firstPlayer.getHandsWon() < 3 || secondPlayer.getHandsWon() == 5) {
            changeServer();
            secondPlayer.setHandsWon(0);
            firstPlayer.setHandsWon(0);
            secondPlayer.setGamesWon(secondPlayer.getGamesWon() + 1);
            if (secondPlayer.getGamesWon() > 1 && (secondPlayer.getGamesWon() - firstPlayer.getGamesWon()) > 1) {
                secondPlayer.setSetsWon(secondPlayer.getSetsWon() + 1);
                setCounter++;
                saveSetResult();
                secondPlayer.setGamesWon(0);
                firstPlayer.setGamesWon(0);
            }
        }
    }

    public boolean noWinner() {
        return firstPlayer.getSetsWon() != 1 && secondPlayer.getSetsWon() != 1;
    }

    private String convertHandsScore(int handsWon) {
        switch (handsWon) {
            case 0:
                return "0";
            case 1:
                return "15";
            case 2:
                return "30";
            case 3:
                return "40";
            case 4:
                return "AD";
            default:
                return "null";
        }
    }

    private void resetValues() {
        firstPlayer.setHandsWon(0);
        firstPlayer.setGamesWon(0);
        firstPlayer.setSetsWon(0);
        firstPlayer.setServing(false);
        secondPlayer.setHandsWon(0);
        secondPlayer.setGamesWon(0);
        secondPlayer.setSetsWon(0);
        secondPlayer.setServing(false);
    }

    private void saveSetResult() {
        String setResult = firstPlayer.getGamesWon() + "-" + secondPlayer.getGamesWon();
        setResults.put(setCounter, setResult);
    }

    private void applySimulationResult(double probability) {
        if (Math.random() < probability) {
            if (firstPlayer.getHandsWon() == 3 && secondPlayer.getHandsWon() == 4) {
                secondPlayer.setHandsWon(secondPlayer.getHandsWon() - 1);
            } else {
                firstPlayer.setHandsWon(firstPlayer.getHandsWon() + 1);
            }
        } else {
            if (secondPlayer.getHandsWon() == 3 && firstPlayer.getHandsWon() == 4) {
                firstPlayer.setHandsWon(firstPlayer.getHandsWon() - 1);
            } else {
                secondPlayer.setHandsWon(secondPlayer.getHandsWon() + 1);
            }
        }
    }
}
