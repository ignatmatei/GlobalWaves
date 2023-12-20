package app.user;

public final class Event {
    private String name;
    private String date;
    private String description;
    /**
     *
     * Constructor with parameters
     */
    public Event(final String name, final String date, final String des) {
        this.name = name;
        this.date = date;
        this.description = des;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(final String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
}
