package app.audio.Files;

import app.audio.LibraryEntry;
import lombok.Getter;

@Getter
public abstract class AudioFile extends LibraryEntry {
    private final Integer duration;

    /**
     * Constructor
     * @param name the name of the file
     * @param duration  the duration of the file
     */
    public AudioFile(final String name, final Integer duration) {
        super(name);
        this.duration = duration;
    }
    public AudioFile() {
        this.duration = null;
    }
}
