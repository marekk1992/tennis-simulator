package tennisSimulator;

import java.util.ArrayList;
import java.util.List;

public class MatchParticipants {

    private final List<Player> participants;

    public MatchParticipants() {
        participants = new ArrayList<>();
    }

    public void addMatchParticipant(Player player) {
        if (participants.size() <= 2) {
            participants.add(player);
        } else {
            System.out.println("You have already chosen two players for a match.");
        }
    }

    public List<Player> getParticipants() {
        return participants;
    }
}
