package tennissimulator.player;

import tennissimulator.utils.IdGenerator;

public class Player implements Comparable<Player> {

    private final String name;
    private final Organization organization;
    private boolean serving;
    private final Attributes attributes;
    private final Integer id;

    public Player(String name, Gender gender, int rating) {
        this.name = name;
        organization = gender.equals(Gender.MAN) ? Organization.ATP : Organization.WTA;
        attributes = new Attributes(rating);
        id = IdGenerator.getId();
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Player player) {
        return Integer.compare(attributes.getRating(), player.getAttributes().getRating());
    }

    public String getAbbreviatedName() {
        String[] splitName = name.split(" ");

        return splitName[1].substring(0, 3).toUpperCase();
    }

    @Override
    public String toString() {
        return id + " | " + name + " | " + organization + "-" + getAttributes().getRating();
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

    public void changeServer() {
        serving = !serving;
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