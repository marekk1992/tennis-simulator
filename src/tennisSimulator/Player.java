package tennisSimulator;

public class Player implements Comparable<Player> {

    private final String name;
    private final String abbreviatedName;
    private final Organization organization;
    private final MatchStats matchStats;
    private final Attributes attributes;

    public Player(String name, Gender gender, int rating) {
        this.name = name;
        String[] splitName = name.split(" ");
        abbreviatedName = splitName[1].substring(0, 3).toUpperCase();
        organization = gender.equals(Gender.MAN) ? Organization.ATP : Organization.WTA;
        matchStats = new MatchStats();
        attributes = new Attributes(rating);
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Player player) {
        return Integer.compare(attributes.getRating(), player.getAttributes().getRating());
    }

    public String getAbbreviatedName() {
        return abbreviatedName;
    }

    @Override
    public String toString() {
        return name + " (" + organization + " rating - " + getAttributes().getRating() + ")";
    }

    public MatchStats getMatchStats() {
        return matchStats;
    }

    public Attributes getAttributes() {
        return attributes;
    }
}
