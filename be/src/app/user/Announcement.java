package app.user;

public final class Announcement {
    private String name;
    private String description;

    public String getName() {
        return name;
    }
    /**
     * Constructor with parameters
     */
    public Announcement(final String name, final String description) {
        this.name = name;
        this.description = description;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
}
