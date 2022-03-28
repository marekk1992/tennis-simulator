package tennisSimulator;

import java.util.Locale;

public class Player implements Comparable<Player> {

    private final String name;
    private final String gender;
    private final String abbreviatedName;
    private final int rating;
    private boolean isServing = false;

    public Player(String name, String gender, int rating) {
        this.name = name;
        this.gender = gender;
        this.rating = rating;
        String [] splitName = name.split(" ");
        abbreviatedName = splitName[1].substring(0, 3).toUpperCase(Locale.ROOT);
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public String getGender() {
        return gender;
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
}
