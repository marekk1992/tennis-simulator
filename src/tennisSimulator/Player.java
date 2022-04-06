package tennisSimulator;

public class Player implements Comparable<Player> {

    private final String name;
    private final String abbreviatedName;
    private final int rating;
    private final String organization;
    private boolean isServing = false;
    private int gamesWon = 0;
    private int setsWon = 0;

    public Player(String name, String gender, int rating) {
        this.name = name;
        this.rating = rating;
        String[] splitName = name.split(" ");
        abbreviatedName = splitName[1].substring(0, 3).toUpperCase();
        organization = gender.equalsIgnoreCase(Gender.MAN.toString())
                ? Organization.ATP.toString() : Organization.WTA.toString();
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
        if (isServing) {
            System.out.println(name + " is serving");
        }
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
}
