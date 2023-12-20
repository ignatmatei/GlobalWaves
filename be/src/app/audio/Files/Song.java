package app.audio.Files;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public final class Song extends AudioFile {
    private final String album;
    private final ArrayList<String> tags;
    private final String lyrics;
    private final String genre;
    private final Integer releaseYear;
    private final String artist;
    private Integer likes;

    /**
     * Constructor
     */
    public Song(final String name, final Integer duration, final String album,
                final ArrayList<String> tags, final String lyrics,
                final String genre, final Integer releaseYear, final String artist) {
        super(name, duration);
        this.album = album;
        this.tags = tags;
        this.lyrics = lyrics;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.artist = artist;
        this.likes = 0;
    }
    /**
     * Constructor
     */
  public Song() {
        super("", 0);
        this.album = "";
        this.tags = new ArrayList<>();
        this.lyrics = "";
        this.genre = "";
        this.releaseYear = 0;
        this.artist = "";
        this.likes = 0;
    }
    /**
     *
     * @param album1 the album to check
     * @return true if the song is in the given album, false otherwise
     */
    @Override
    public boolean matchesAlbum(final String album1) {
        return this.getAlbum().equalsIgnoreCase(album1);
    }

    /**
     *
     * @param tags1 the tags to check
     * @return true if the song contains all the tags, false otherwise
     */
    @Override
    public boolean matchesTags(final ArrayList<String> tags1) {
        List<String> songTags = new ArrayList<>();
        for (String tag : this.getTags()) {
            songTags.add(tag.toLowerCase());
        }

        for (String tag : tags1) {
            if (!songTags.contains(tag.toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param lyrics1 the lyrics to check
     * @return true if the song contains the given lyrics, false otherwise
     */
    @Override
    public boolean matchesLyrics(final String lyrics1) {
        return this.getLyrics().toLowerCase().contains(lyrics1.toLowerCase());
    }

    /**
     *
     * @param genre1 the genre to check
     * @return true if the song is of the given genre, false otherwise
     */
    @Override
    public boolean matchesGenre(final String genre1) {
        return this.getGenre().equalsIgnoreCase(genre1);
    }

    /**
     *
     * @param artist1 the artist to check
     * @return true if the song is by the given artist, false otherwise
     */
    @Override
    public boolean matchesArtist(final String artist1) {
        return this.getArtist().equalsIgnoreCase(artist1);
    }

    /**
     *
     * @param releaseYear1 the release year to check
     * @return true if the song was released in the given year, false otherwise
     */
    @Override
    public boolean matchesReleaseYear(final String releaseYear1) {
        return filterByYear(this.getReleaseYear(), releaseYear1);
    }

    /**
     *
     * @param year the year to check
     * @param query the query to check
     * @return true if the year matches the query, false otherwise
     */
    private static boolean filterByYear(final int year, final String query) {
        if (query.startsWith("<")) {
            return year < Integer.parseInt(query.substring(1));
        } else if (query.startsWith(">")) {
            return year > Integer.parseInt(query.substring(1));
        } else {
            return year == Integer.parseInt(query);
        }
    }

    /**
     * adds a like to the song
     */
    public void like() {
        likes++;
    }

    /**
     * removes a like from the song
     */
    public void dislike() {
        likes--;
    }
}
