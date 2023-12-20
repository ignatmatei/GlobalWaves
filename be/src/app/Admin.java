package app;

import app.audio.Collections.Album;
import app.audio.Collections.Playlist;
import app.audio.Collections.Podcast;
import app.audio.Files.Episode;
import app.audio.Files.Song;
import app.user.Artist;
import app.user.Host;
import app.user.NormalUser;
import app.user.User;
import fileio.input.UserInput;
import fileio.input.SongInput;
import fileio.input.PodcastInput;
import fileio.input.EpisodeInput;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
public final class Admin {
    /**
     * -- GETTER --
     *
     * @return the user
     */
    @Getter
    private static List<User> users = new ArrayList<>();
    private static List<Artist> artists = new ArrayList<>();
    private static List<Host> hosts = new ArrayList<>();
    private static List<Song> songs = new ArrayList<>();
    private static List<Podcast> podcasts = new ArrayList<>();
    private static List<Album> albums = new ArrayList<>();
    private static int timestamp = 0;
    private static int noOfSearchResults = 5;
    private Admin() {

    }
    /**
     * sets users
     * @param userInputList the list of users
     */
    public static void setUsers(final List<UserInput> userInputList) {
        users = new ArrayList<>();
        for (UserInput userInput : userInputList) {
            users.add(new NormalUser(userInput.getUsername(),
                    userInput.getAge(), userInput.getCity()));
            users.get(users.size() - 1).setType("user");
        }
    }
    /**
     * adds album
     * @param album the album to be added
     */
    public static void addAlbum(final Album album) {
        albums.add(album);
    }
    /**
     * adds user
     * @param userInput the user to be added
     */
    public static void addUser(final UserInput userInput) {
        users.add(new User(userInput.getUsername(), userInput.getAge(), userInput.getCity()));
        users.get(users.size() - 1).setType("user");
        }
        /**
         * adds artist
         * @param userInput the artist to be added
         */
        public static void addArtist(final UserInput userInput) {
        users.add(new Artist(userInput.getUsername(), userInput.getAge(), userInput.getCity()));
        artists.add(new Artist(userInput.getUsername(), userInput.getAge(), userInput.getCity()));
        users.get(users.size() - 1).setType("artist");
        }
        /**
         * adds host
         * @param userInput the host to be added
         */
        public static void addHost(final UserInput userInput) {
        users.add(new Host(userInput.getUsername(), userInput.getAge(), userInput.getCity()));
        hosts.add(new Host(userInput.getUsername(), userInput.getAge(), userInput.getCity()));
        users.get(users.size() - 1).setType("host");
        }

    public static void setArtists(final List<Artist> artists) {
        Admin.artists = artists;
    }

    public static int getNoOfSearchResults() {
        return noOfSearchResults;
    }

    /**
     * sets songs
     * @param songInputList the list of songs
     */
    public static void setSongs(final List<SongInput> songInputList) {
        songs = new ArrayList<>();
        for (SongInput songInput : songInputList) {
            songs.add(new Song(songInput.getName(), songInput.getDuration(), songInput.getAlbum(),
                    songInput.getTags(), songInput.getLyrics(), songInput.getGenre(),
                    songInput.getReleaseYear(), songInput.getArtist()));
        }
    }

    /**
     * sets podcasts
     * @param podcastInputList the list of podcasts
     */
    public static void setPodcasts(final List<PodcastInput> podcastInputList) {
        podcasts = new ArrayList<>();
        for (PodcastInput podcastInput : podcastInputList) {
            List<Episode> episodes = new ArrayList<>();
            for (EpisodeInput episodeInput : podcastInput.getEpisodes()) {
                episodes.add(new Episode(episodeInput.getName(),
                        episodeInput.getDuration(), episodeInput.getDescription()));
            }
            podcasts.add(new Podcast(podcastInput.getName(), podcastInput.getOwner(), episodes));
        }
    }
    /**
     * adds podcast
     * @param podcast the podcast to be added
     */
    public static void addPodcast(final Podcast podcast) {
        podcasts.add(podcast);
    }
    /**
     * removes podcast
     * @param podcast the podcast to be removed
     */
    public static void removePodcast(final Podcast podcast) {

    }
    public static List<Album> getAlbums() {
        return albums;
    }

    public static void setAlbums(final List<Album> albums) {
        Admin.albums = albums;
    }

    /**
     *
     * @return all the songs
     */

    public static List<Song> getSongs() {
        return new ArrayList<>(songs);
    }

    /**
     *
     * @return all the podcasts
     */
    public static List<Podcast> getPodcasts() {
        return new ArrayList<>(podcasts);

    }

    /**
     *
     * @return all the artists
     */
    public static List<Artist> getArtists() {
        return new ArrayList<>(artists);
    }
    public static List<Host> getHosts() {
        return new ArrayList<>(hosts);
    }
    /**
     *
     * @return all the playlists
     */
    public static List<Playlist> getPlaylists() {
        List<Playlist> playlists = new ArrayList<>();
        for (User user : users) {
            playlists.addAll(user.getPlaylists());
        }
        return playlists;
    }

    /**
     *
     * @param username of the user
     * @return the user with the given username
     */
    public static User getUser(final String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
    /**
     *
     * @param name of the album
     * @return the album with the given name
     */
    public static Album getAlbum(final String name) {
        for (Album album : albums) {
            if (album.getName().equals(name)) {
                return album;
            }
        }
        return null;
    }

    /**
     *
     * @param name of the podcast
     * @return the podcast with the given name
     */
    public static Podcast getPodcast(final String name) {
        for (Podcast podcast : podcasts) {
            if (podcast.getName().equals(name)) {
                return podcast;
            }
        }
        return null;
    }
    /**
     *
     * @param newTimestamp the new timestamp
     *                    updates the timestamp and simulates the time for all the users
     */
    public static void updateTimestamp(final int newTimestamp) {
        int elapsed = newTimestamp - timestamp;
        timestamp = newTimestamp;
        if (elapsed == 0) {
            return;
        }

        for (User user : users) {
            user.simulateTime(elapsed);
        }
    }

    /**
     *
     * @return top 5 songs by likes
     */
    public static List<String> getTop5Songs() {
        List<Song> sortedSongs = new ArrayList<>(songs);
        sortedSongs.sort(Comparator.comparingInt(Song::getLikes).reversed());
        List<String> topSongs = new ArrayList<>();
        int count = 0;
        for (Song song : sortedSongs) {
            if (count >= noOfSearchResults) {
                break;
            }
            topSongs.add(song.getName());
            count++;
        }
        return topSongs;
    }
    /**
     *
     * @return top 5 albums by likes
     */
    public static List<String> getTop5Albums() {
        List<Album> sortedAlbums = new ArrayList<>(albums);
        for (Album album : sortedAlbums) {
            album.calculateLikes();
        }
        // sort albums by likes and then lexicographically
        sortedAlbums.sort(Comparator.comparingInt(Album::getNumberOfLikes).reversed()
                .thenComparing(Album::getName, Comparator.naturalOrder()));
        List<String> topAlbums = new ArrayList<>();
        int count = 0;
        for (Album album : sortedAlbums) {
            if (count >= noOfSearchResults) {
                break;
            }
            topAlbums.add(album.getName());
            count++;
        }
        return topAlbums;
    }

    /**
     *
     * @return top 5 artists by likes
     */
    public static List<String> getTop5Artists() {
        List<Artist> sortedArtists = new ArrayList<>();
        for (User user : users) {
            if (user.getType().equals("artist")) {
                sortedArtists.add((Artist) user);
            }
        }
        sortedArtists.sort(Comparator.comparingInt(Artist::getNumberOfLikes).reversed());
        List<String> topArtists = new ArrayList<>();
        int count = 0;
        for (Artist artist : sortedArtists) {
            if (count >= noOfSearchResults) {
                break;
            }
            topArtists.add(artist.getUsername());
            count++;
        }
        return topArtists;
    }
    /**
     *
     * @return top 5 playlists by number of followers
     */
    public static List<String> getTop5Playlists() {
        List<Playlist> sortedPlaylists = new ArrayList<>(getPlaylists());
        sortedPlaylists.sort(Comparator.comparingInt(Playlist::getFollowers)
                .reversed()
                .thenComparing(Playlist::getTimestamp, Comparator.naturalOrder()));
        List<String> topPlaylists = new ArrayList<>();
        int count = 0;
        for (Playlist playlist : sortedPlaylists) {
            if (count >= noOfSearchResults) {
                break;
            }
            topPlaylists.add(playlist.getName());
            count++;
        }
        return topPlaylists;
    }
    /**
     * resets data for each test
     */
    public static void reset() {
        users = new ArrayList<>();
        songs = new ArrayList<>();
        podcasts = new ArrayList<>();
        artists = new ArrayList<>();
        hosts = new ArrayList<>();
        timestamp = 0;
        albums = new ArrayList<>();
    }
    /**
     * adds song
     * @param song the song to be added
     */
    public static void addSong(final Song song) {
       /* songs.add(new Song(song.getName(), song.getDuration(), song.getAlbum(),
                song.getTags(), song.getLyrics(), song.getGenre(),
                song.getReleaseYear(), song.getArtist()));*/
        songs.add(song);
    }

    /**
     *
     * @param song the song to be removed
     */
    public static void removeSong(final Song song) {
        for (User user : Admin.getUsers()) {
            user.getLikedSongs().remove(song);
        }
        songs.remove(song);
    }
/**
     *
     * @param playlist the podcast to be removed
     */
///not safe code
    public static void removePlaylist(final Playlist playlist) {
        for (User user : Admin.getUsers()) {
            user.getFollowedPlaylists().remove(playlist);
            Admin.getPlaylists().remove(playlist);
        }
    }

    /**
     *
     * @param user the user to be removed anf to remove his follow
     */
    public static void removeAFollow(final User user) {
        for (Playlist playlist : Admin.getPlaylists()) {
            if (user.getFollowedPlaylists().contains(playlist)) {
                playlist.setFollowers(playlist.getFollowers() - 1);
               // user.getFollowedPlaylists().remove(playlist);
            }
        }
    }
}
