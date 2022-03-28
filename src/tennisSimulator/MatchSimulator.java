package tennisSimulator;

public class MatchSimulator {

    public double calculateWinProbability(int rating) {
        return 0;
    }

    public void simulateHand(Player one, Player two) {
    }

    public void firstServe(Player firstPlayer, Player secondPLayer)  {
        double random = Math.random();
        if (random >= 0.5) {
            firstPlayer.setServing(true);
            System.out.println(firstPlayer.getName() + " is serving");
        } else {
            secondPLayer.setServing(true);
            System.out.println(secondPLayer.getName() + " is serving");
        }
    }

    public void changeServe(Player firstPlayer, Player secondPlayer) {
        if (!firstPlayer.isServing()) {
            firstPlayer.setServing(true);
            secondPlayer.setServing(false);
        } else {
            firstPlayer.setServing(false);
            secondPlayer.setServing(true);
        }
    }

    public void showResult() {

    }
}
