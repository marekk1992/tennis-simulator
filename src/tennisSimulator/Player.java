package tennisSimulator;

public class Player implements Comparable<Player> {

    private String name;
    private String gender;
    private int atpRating;

    public Player(String name, String gender, int atpRating) {
        this.name = name;
        this.gender = gender;
        this.atpRating = atpRating;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAtpRating() {
        return atpRating;
    }

    @Override
    public int compareTo(Player player) {
        if (atpRating > player.atpRating) {
            return 1;
        } else if (atpRating < player.atpRating) {
            return -1;
        } else {
            return 0;
        }
    }
}
