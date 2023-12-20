package app.utils;

import java.util.List;

public final class AlbumOutput {
    private String name;
    private List<String> songs;
    /**
     * Constructor
     */
    public AlbumOutput() {

    }
    /**
     * Constructor
     */
    public AlbumOutput(final String name, final List<String> songs) {
        this.name = name;
        this.songs = songs;
    }
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<String> getSongs() {
        return songs;
    }

    public void setSongs(final List<String> songs) {
        this.songs = songs;
    }
}
