package tennisSimulator;

public class MatchSimulator {

    public void firstServe(Player firstPlayer, Player secondPLayer)  {
        if (Math.random() >= 0.5) {
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
}
