package app.audio.Collections;

import app.audio.Files.AudioFile;
import app.audio.LibraryEntry;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

@Getter
public abstract class AudioCollection extends LibraryEntry {
    @JsonIgnore
    private final String owner;

    /**
     * Constructor
     */
    public AudioCollection(final String name, final String owner) {
        super(name);
        this.owner = owner;
    }
    /**
     * Constructor
     */
    public AudioCollection(final String name) {
        super(name);
        this.owner = null;
    }

    /**
     *
     * @return the number of tracks in the collection
     */
    @JsonIgnore
    public abstract int getNumberOfTracks();

    /**
     *
     * @param index the index of the track
     * @return the track at the given index
     */
    public abstract AudioFile getTrackByIndex(int index);

    /**
     *
     * @param user the username to check
     * @return true if the given user is the owner of the collection, false otherwise
     */
    @Override
    public boolean matchesOwner(final String user) {
        return this.getOwner().equals(user);
    }
}
