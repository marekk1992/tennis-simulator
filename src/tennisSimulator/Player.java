package tennisSimulator;

public class Player implements Comparable<Player> {

    private final String name;
    private final String abbreviatedName;
    private final int rating;
    private final Organization organization;
    private boolean isServing = false;
    private int gamesWon = 0;
    private int setsWon = 0;
    private int handsWon = 0;

    public Player(String name, Gender gender, int rating) {
        this.name = name;
        this.rating = rating;
        String[] splitName = name.split(" ");
        abbreviatedName = splitName[1].substring(0, 3).toUpperCase();
        organization = gender.equals(Gender.MAN) ? Organization.ATP : Organization.WTA;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Player player) {
        return Integer.compare(rating, player.rating);
    }

    public String getAbbreviatedName() {
        return abbreviatedName;
    }

    public boolean isServing() {
        return isServing;
    }

    public void setServing(boolean serving) {
        isServing = serving;
    }

    @Override
    public String toString() {
        return name + " (" + organization + " rating - " + rating + ")";
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public int getSetsWon() {
        return setsWon;
    }

    public int getHandsWon() {
        return handsWon;
    }

    public int getRating() {
        return rating;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public void setSetsWon(int setsWon) {
        this.setsWon = setsWon;
    }

    public void setHandsWon(int handsWon) {
        this.handsWon = handsWon;
    }
}
