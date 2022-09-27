package tennisSimulator.player;

public class Player implements Comparable<Player> {

    private final String name;
    private final String abbreviatedName;
    private final Organization organization;
    private boolean serving;
    private final Attributes attributes;
    private final Integer id;
    private static int counter = 0;

    public Player(String name, Gender gender, int rating) {
        this.name = name;
        String[] splitName = name.split(" ");
        abbreviatedName = splitName[1].substring(0, 3).toUpperCase();
        organization = gender.equals(Gender.MAN) ? Organization.ATP : Organization.WTA;
        attributes = new Attributes(rating);
        counter++;
        id = counter;
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
        return id + " | " + name + " | " + organization + " rating - " + getAttributes().getRating();
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public int getRating() {
        return attributes.getRating();
    }

    public boolean isServing() {
        return serving;
    }

    public void setServing(boolean serving) {
        this.serving = serving;
    }

    public String getOrganization() {
        return organization.toString();
    }

    public Integer getId() {
        return id;
    }
}
