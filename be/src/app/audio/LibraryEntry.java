package app.audio;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public abstract class LibraryEntry {
    private final String name;

    /**
     * Constructor
     * @param name the name of the entry
     */
    public LibraryEntry(final String name) {

        this.name = name;
    }
    /**
     * Constructor
     */
public LibraryEntry() {
        this.name = "";
}
    /**
     *
     * @param name1 the name to match
     * @return true if the name matches
     */
    public boolean matchesName(final String name1) {

        return getName().toLowerCase().startsWith(name1.toLowerCase());
    }

    /**
     *
     * @param album the album to match
     * @return true if the album matches
     */
    public boolean matchesAlbum(final String album) {
        return false;
    }

    /**
     *
     * @param tags the tags to match
     * @return true if the tags match
     */
    public boolean matchesTags(final ArrayList<String> tags) {
        return false;
    }

    /**
     *
     * @param lyrics the lyrics to match
     * @return true if the lyrics match
     */
    public boolean matchesLyrics(final String lyrics) {
        return false;
    }

    /**
     *
     * @param genre the genre to match
     * @return true if the genre matches
     */
    public boolean matchesGenre(final String genre) {
        return false;
    }

    /**
     *
     * @param artist the artist to match
     * @return true if the artist matches
     */
    public boolean matchesArtist(final String artist) {
        return false;
    }

    /**
     *
     * @param releaseYear the release year to match
     * @return true if the release year matches
     */
    public boolean matchesReleaseYear(final String releaseYear) {
        return false;
    }
    /**
     *
     * @param user the user to match
     * @return true if the user is the owner
     */
    public boolean matchesOwner(final String user) {
        return false;
    }

    /**
     *
     * @param user the searched user
     * @return true if the playlist is visible to the searched user
     */
    public boolean isVisibleToUser(final String user) {
        return false;
    }

    /**
     *
     * @param followers the number of followers to match
     * @return true if the playlist has the given number of followers
     */
    public boolean matchesFollowers(final String followers) {
        return false;
    }
}
