package app.player;

import lombok.Getter;

@Getter
public class PodcastBookmark {
    private final String name;
    private final int id;
    private final int timestamp;

    /**
     * Constructor
     */
    public PodcastBookmark(final String name, final int id, final int timestamp) {
        this.name = name;
        this.id = id;
        this.timestamp = timestamp;
    }

    /**
     * toString
     * @return the string representation of the bookmark
     */
    @Override
    public String toString() {
        return "PodcastBookmark{"
                +
                "name='" + name + '\''
                +
                ", id=" + id
                +
                ", timestamp=" + timestamp
                +
                '}';
    }
}
