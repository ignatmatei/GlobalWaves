package app.searchBar;

import app.audio.LibraryEntry;

import java.util.ArrayList;
import java.util.List;

public final class FilterUtils {
    private FilterUtils() {
    }
 /**
     * Filters the given entries by name
     * @param entries
     * @param name
     * @return the filtered entries
     */
    public static List<LibraryEntry> filterByName(final List<LibraryEntry> entries,
                                                  final String name) {
        List<LibraryEntry> result = new ArrayList<>();
        for (LibraryEntry entry : entries) {
            if (entry.matchesName(name)) {
                result.add(entry);
            }
        }
        return result;
    }
    /**
     * Filters the given entries by album
     * @param entries
     * @param album
     * @return
     */
    public static List<LibraryEntry> filterByAlbum(final List<LibraryEntry> entries,
                                                   final String album) {
        return filter(entries, entry -> entry.matchesAlbum(album));
    }

    /**
     * Filters the given entries by tags
     * @param entries
     * @param tags
     * @return
     */
    public static List<LibraryEntry> filterByTags(final List<LibraryEntry> entries,
                                                  final ArrayList<String> tags) {
        return filter(entries, entry -> entry.matchesTags(tags));
    }

    /**
     * Filters the given entries by lyrics
     * @param entries
     * @param lyrics
     * @return
     */
    public static List<LibraryEntry> filterByLyrics(final List<LibraryEntry> entries,
                                                    final String lyrics) {
        return filter(entries, entry -> entry.matchesLyrics(lyrics));
    }

    /**
     * Filters the given entries by genre
     * @param entries
     * @param genre
     * @return
     */
    public static List<LibraryEntry> filterByGenre(final List<LibraryEntry> entries,
                                                   final String genre) {
        return filter(entries, entry -> entry.matchesGenre(genre));
    }

    /**
     * Filters the given entries by artist
     * @param entries
     * @param artist
     * @return
     */
    public static List<LibraryEntry> filterByArtist(final List<LibraryEntry> entries,
                                                    final String artist) {
        return filter(entries, entry -> entry.matchesArtist(artist));
    }

    /**
     * Filters the given entries by release year
     * @param entries
     * @param releaseYear
     * @return
     */
    public static List<LibraryEntry> filterByReleaseYear(final List<LibraryEntry> entries,
                                                         final String releaseYear) {
        return filter(entries, entry -> entry.matchesReleaseYear(releaseYear));
    }

    /**
     * Filters the given entries by owner
     * @param entries
     * @param user
     * @return
     */
    public static List<LibraryEntry> filterByOwner(final List<LibraryEntry> entries,
                                                   final String user) {
        return filter(entries, entry -> entry.matchesOwner(user));
    }
    /**
     * Filters the given entries by playlist visibility
     * @param entries
     * @param user
     * @return
     */
    public static List<LibraryEntry> filterByPlaylistVisibility(final List<LibraryEntry> entries,
                                                                final String user) {
        return filter(entries, entry -> entry.isVisibleToUser(user));
    }

    /**
     * Filters the given entries by followers
     * @param entries
     * @param followers
     * @return
     */
    public static List<LibraryEntry> filterByFollowers(final List<LibraryEntry> entries,
                                                       final String followers) {
        return filter(entries, entry -> entry.matchesFollowers(followers));
    }

    /**
     * Filters the given entries by a given criteria
     * @param entries
     * @param criteria
     * @return
     */
    private static List<LibraryEntry> filter(final List<LibraryEntry> entries,
                                             final FilterCriteria criteria) {
        List<LibraryEntry> result = new ArrayList<>();
        for (LibraryEntry entry : entries) {
            if (criteria.matches(entry)) {
                result.add(entry);
            }
        }
        return result;
    }

    /**
     * Functional interface for filtering
     */
    @FunctionalInterface
    private interface FilterCriteria {
        boolean matches(LibraryEntry entry);
    }
}
