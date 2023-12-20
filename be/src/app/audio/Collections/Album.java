package app.audio.Collections;
import app.audio.Files.AudioFile;
import app.audio.Files.Song;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public final class Album extends AudioCollection {
    private List<Song> songs;
    private int numberOfLikes;
    /**
     * Constructor
     */
    public Album(final String name, final List<Song> songs) {
        super(name);
        this.songs = songs;
        numberOfLikes = 0;
    }
    /**
     * Constructor
     */
public Album(final String name, final List<Song> songs, final String owner) {
        super(name, owner);
        this.songs = songs;
        numberOfLikes = 0;
    }

    @Override
    public int getNumberOfTracks() {
        return songs.size();
    }

    @Override
    public AudioFile getTrackByIndex(final int index) {
        return songs.get(index);
    }

    /**
     *
     * @return the names of the songs
     */
    public List<String> getSongsNames() {
        List<String> songsNames = new ArrayList<>();
        for (Song song : songs) {
            songsNames.add(song.getName());
        }
        return songsNames;
    }

    /**
     * Calculates the number of likes
     */
    public void calculateLikes() {
    numberOfLikes = 0;
        for (Song song : songs) {
            numberOfLikes += song.getLikes();
        }
    }
}
