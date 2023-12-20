package app.audio.Collections;

import app.audio.Files.AudioFile;
import app.audio.Files.Song;
import app.utils.Enums;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public final class Playlist extends AudioCollection {
    private final ArrayList<Song> songs;
    private Enums.Visibility visibility;
    private Integer followers;
    private int timestamp;

    /**
     * Constructor
     */
    public Playlist(final String name, final String owner) {
        this(name, owner, 0);
    }

    /**
     * Constructor
     */
    public Playlist(final String name, final String owner, final int timestamp) {
        super(name, owner);
        this.songs = new ArrayList<>();
        this.visibility = Enums.Visibility.PUBLIC;
        this.followers = 0;
        this.timestamp = timestamp;
    }

    /**
     * Checks if the playlist contains the given song
     * @param song the song to check
     * @return true if the playlist contains the song, false otherwise
     */
    public boolean containsSong(final Song song) {
        return songs.contains(song);
    }

    /**
     * Adds a song to the playlist
     * @param song the song to add
     */
    public void addSong(final Song song) {
        songs.add(song);
    }

    /**
     * Removes a song from the playlist
     * @param song the song to remove
     */
    public void removeSong(final Song song) {
        songs.remove(song);
    }

    /**
     * Removes a song from the playlist
     * @param index the index of the song
     */
    public void removeSong(final int index) {
        songs.remove(index);
    }
/**
     * Sets the visibility of the playlist
     *
     */
    public void switchVisibility() {
        if (visibility == Enums.Visibility.PUBLIC) {
            visibility = Enums.Visibility.PRIVATE;
        } else {
            visibility = Enums.Visibility.PUBLIC;
        }
    }

    /**
     * Increases the number of followers by 1
     */
    public void increaseFollowers() {
        followers++;
    }

    /**
     * Decreases the number of followers by 1
     */
    public void decreaseFollowers() {
        followers--;
    }

    /**
     *
     * @return the number of tracks in the playlist
     */
    @Override
    public int getNumberOfTracks() {
        return songs.size();
    }

    /**
     *
     * @param index the index of the track
     * @return the track at the given index
     */
    @Override
    public AudioFile getTrackByIndex(final int index) {
        return songs.get(index);
    }

    /**
     *
     * @param user the username to check
     * @return true if the playlist is visible to the user, false otherwise
     */
    @Override
    public boolean isVisibleToUser(final String user) {
        return this.getVisibility() == Enums.Visibility.PUBLIC
                ||
                (this.getVisibility() == Enums.Visibility.PRIVATE && this.getOwner().equals(user));
    }

    /**
     *
     * @param followers the number of followers
     * @return true if the playlist has the given number of followers, false otherwise
     */
    @Override
    public boolean matchesFollowers(final String followers1) {
        return filterByFollowersCount(this.getFollowers(), followers1);
    }

    /**
     *
     * @param count the number of followers
     * @param query the query to filter by
     * @return true if the playlist has the given number of followers, false otherwise
     */
    private static boolean filterByFollowersCount(final int count, final String query) {
        if (query.startsWith("<")) {
            return count < Integer.parseInt(query.substring(1));
        } else if (query.startsWith(">")) {
            return count > Integer.parseInt(query.substring(1));
        } else {
            return count == Integer.parseInt(query);
        }
    }

    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(final Integer followers) {
        this.followers = followers;
    }
}
