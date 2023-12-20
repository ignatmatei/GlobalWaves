package app.user;

import app.Admin;
import app.audio.Collections.Album;
import app.audio.Files.Song;

import java.util.ArrayList;
import java.util.List;

public final class Artist extends User {
    private List<Album> albums;
    private List<Merch> merch;
    private List<Event> events;
    /**
     * Constructor
     * @param username the username of the artist
     * @param age the age of the artist
     * @param city the city of the artist
     */
    public Artist(final String username, final int age, final String city) {
        super(username, age, city);
        albums = new ArrayList<>();
        merch = new ArrayList<>();
        events = new ArrayList<>();
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(final List<Event> events) {
        this.events = events;
    }

    public List<Merch> getMerch() {
        return merch;
    }

    public void setMerch(final List<Merch> merch) {
        this.merch = merch;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(final List<Album> albums) {
        this.albums = albums;
    }

    /**
     *
     * @param album the album to be added
     */
    public void addAlbum(final Album album) {
        albums.add(album);
    }
    /**
     *
     * @param merch1 the merch to be added
     */
    public void addMerch(final Merch merch1) {
        this.merch.add(merch1);
    }

    /**
     * adds event
     * @param event the event to be added
     */
    public void addEvent(final Event event) {
        events.add(event);
    }

    /**
     *
     * @return artist's page
     */
    public String printArtist() {
        String output = "Albums:\n\t[";
        for (Album album : albums) {
            output += album.getName();
            if (albums.indexOf(album) != albums.size() - 1) {
                output += ",";
            }
            output += " ";
        }
        if (albums.size() > 0) {
            output = output.substring(0, output.length() - 1);
        }
        output += "]\n\nMerch:\n\t[";
        for (Merch merch1 : merch) {
            output += merch1.getName() + " - ";
            output += merch1.getPrice() + ":\n\t";
            output += merch1.getDescription() + ", ";
        }
        if (merch.size() > 0) {
            output = output.substring(0, output.length() - 2);
        }
        output += "]\n\nEvents:\n\t[";
        for (Event event : events) {
            output += event.getName() + " - "
                    +
                    event.getDate() + ":\n\t"
                    +
                    event.getDescription() + ", ";
        }
        if (events.size() > 0) {
            output = output.substring(0, output.length() - 2);
        }
        output += "]";
        return output;
    }
    /**
     *
     * @return the number of likes
     */
    public int getNumberOfLikes() {
        int likes = 0;
        for (Song song : Admin.getSongs()) {
            if (song.getArtist().equals(this.getUsername())) {
                likes += song.getLikes();
            }
        }
        return likes;
    }
}
