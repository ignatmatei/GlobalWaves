package app.user;

import app.Admin;
import app.audio.Collections.AudioCollection;
import app.audio.Collections.Playlist;
import app.audio.Collections.PlaylistOutput;
import app.audio.Files.AudioFile;
import app.audio.Files.Song;
import app.audio.LibraryEntry;
import app.player.Player;
import app.player.PlayerStats;
import app.searchBar.Filters;
import app.searchBar.SearchBar;
import app.utils.Enums;
import lombok.Getter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class User extends LibraryEntry {
    @Getter
    private String username;
    @Getter
    private int age;
    @Getter
    private String city;
    @Getter
    private ArrayList<Playlist> playlists;
    @Getter
    private ArrayList<Song> likedSongs;
    @Getter
    private ArrayList<Playlist> followedPlaylists;
    private final Player player;
    private final SearchBar searchBar;
    private boolean lastSearched;
    private boolean isOnline;
    private String type;
    private String page;
    private String lastSelectedHost;
    private String currentEntityName; //pentru a afla
    // pe pagina carui artist/host e user ul
    /**
     * Constructor
     */
    public User(final String username, final int age, final String city) {
        super(username);
        this.username = username;
        this.age = age;
        this.city = city;
        playlists = new ArrayList<>();
        likedSongs = new ArrayList<>();
        followedPlaylists = new ArrayList<>();
        player = new Player();
        searchBar = new SearchBar(username);
        lastSearched = false;
        isOnline = true;
        currentEntityName = "";
    }

    /**
     *
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }
/**
     *
     * @return the search bar
     */
    public SearchBar getSearchBar() {
        return searchBar;
    }

    /**
     *
     * @return the username of the user
     */
    public String getPage() {
        return page;
    }

    /**
     *
     * @param page the page of the user
     */
    public void setPage(final String page) {
        this.page = page;
        if (page.equals("Home")) {
            currentEntityName = "";
        }
        if (page.equals("LikedContent")) {
            currentEntityName = "";
        }
    }

    /**
     *
     * @return the type of the audio user
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type the type of the user
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     *
     * @return whether the user is online or not
     */
    public boolean isOnline() {
        return isOnline;
    }

    /**
     *
     * @param online the online state
     */
    public void setOnline(final boolean online) {
        isOnline = online;
    }

    /**
     *
     * @return the current entity name
     */
    public String getCurrentEntityName() {
        return currentEntityName;
    }

    /**
     *
     * @param filters the filters to be applied
     * @param type1 the type of the audio file
     * @return the search results
     */
    public ArrayList<String> search(final Filters filters, final String type1) {
        searchBar.clearSelection();
        player.stop();
        lastSearched = true;
        ArrayList<String> results = new ArrayList<>();
            List<LibraryEntry> libraryEntries = searchBar.search(filters, type1);

            for (LibraryEntry libraryEntry : libraryEntries) {
                results.add(libraryEntry.getName());
            }
            return results;
    }
    /**
     *
     * @param itemNumber the number of the item to be selected
     * @return the result of the selection
     */
    public String select(final int itemNumber) {
        if (!lastSearched) {
            return "Please conduct a search before making a selection.";
        }

        lastSearched = false;

        LibraryEntry selected = searchBar.select(itemNumber);

        if (selected == null) {
            return "The selected ID is too high.";
        }
        for (Artist artist : Admin.getArtists()) {
            if (artist.getName().equals(selected.getName())) {
                this.page = "Artist";
                this.currentEntityName = artist.getName();
                return "Successfully selected "
                        +
                        artist.getName() + "'s page.";
            }
        }
        for (Host host : Admin.getHosts()) {
            if (host.getName().equals(selected.getName())) {
                this.page = "Host";
                this.currentEntityName = host.getName();
                lastSelectedHost = host.getName();
                return "Successfully selected "
                        +
                        host.getName() + "'s page.";
            }
        }
        return "Successfully selected %s.".formatted(selected.getName());
    }

    /**
     *
     * @return the result of the load operation
     */
    public String load() {
        if (searchBar.getLastSelected() == null) {
            return "Please select a source before attempting to load.";
        }

        if (!searchBar.getLastSearchType().equals("song")
                && ((AudioCollection) searchBar.getLastSelected()).getNumberOfTracks() == 0) {
            return "You can't load an empty audio collection!";
        }

        player.setSource(searchBar.getLastSelected(), searchBar.getLastSearchType());
        searchBar.clearSelection();

        player.pause();

        return "Playback loaded successfully.";
    }

    /**
     *
     * @return the result of the play/pause operations
     */
    public String playPause() {
        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before attempting to pause or resume playback.";
        }

        player.pause();

        if (player.getPaused()) {
            return "Playback paused successfully.";
        } else {
            return "Playback resumed successfully.";
        }
    }

    /**
     *
     * @return the result of the repeat operation
     */
    public String repeat() {
        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before setting the repeat status.";
        }

        Enums.RepeatMode repeatMode = player.repeat();
        String repeatStatus = "";
        switch (repeatMode) {
            case NO_REPEAT -> repeatStatus = "no repeat";
            case REPEAT_ONCE -> repeatStatus = "repeat once";
            case REPEAT_ALL -> repeatStatus = "repeat all";
            case REPEAT_INFINITE -> repeatStatus = "repeat infinite";
            case REPEAT_CURRENT_SONG -> repeatStatus = "repeat current song";
            default -> {
            }
        }

        return "Repeat mode changed to %s.".formatted(repeatStatus);
    }

    /**
     *
     * @param seed the seed for the shuffle operation
     * @return the result of the shuffle operation
     */
    public String shuffle(final Integer seed) {
        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before using the shuffle function.";
        }
        if (!player.getType().equals("playlist") && !player.getType().equals("album")) {
            return "The loaded source is not a playlist or an album.";
        }
        player.shuffle(seed);

        if (player.getShuffle()) {
            return "Shuffle function activated successfully.";
        }
        return "Shuffle function deactivated successfully.";
    }

    /**
     *
     * @return the result of the stop operation
     */
    public String forward() {
        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before attempting to forward.";
        }
        if (!player.getType().equals("podcast")) {
            return "The loaded source is not a podcast.";
        }
        player.skipNext();

        return "Skipped forward successfully.";
    }

    /**
     *
     * @return the result of the stop operation
     */
    public String backward() {
        if (player.getCurrentAudioFile() == null) {
            return "Please select a source before rewinding.";
        }
        if (!player.getType().equals("podcast")) {
            return "The loaded source is not a podcast.";
        }
        player.skipPrev();

        return "Rewound successfully.";
    }

    /**
     *
     * @return the result of the stop operation
     */
    public String like() {
        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before liking or unliking.";
        }
        if (!player.getType().equals("song") && !player.getType().equals("playlist")
            && !player.getType().equals("album")) {
            return "Loaded source is not a song.";
        }
        Song song = (Song) player.getCurrentAudioFile();

        if (likedSongs.contains(song)) {
            likedSongs.remove(song);
            song.dislike();

            return "Unlike registered successfully.";
        }

        likedSongs.add(song);
        song.like();
        return "Like registered successfully.";
    }

    /**
     *
     * @return the result of the next operation
     */
    public String next() {
        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before skipping to the next track.";
        }
        player.next();

        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before skipping to the next track.";
        }
        return
                "Skipped to next track successfully. The current track is %s."
                        .formatted(player.getCurrentAudioFile().getName());
    }

    /**
     *
     * @return the result of the previous operation
     */
    public String prev() {
        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before returning to the previous track.";
        }
        player.prev();

        return
                "Returned to previous track successfully. The current track is %s."
                        .formatted(player.getCurrentAudioFile().getName());
    }

    /**
     *
     * @param name the name of the playlist to be created
     * @param timestamp the timestamp at which the playlist is created
     * @return the result of the createPlaylist operation
     */
    public String createPlaylist(final String name, final int timestamp) {
        if (playlists.stream().anyMatch(playlist -> playlist.getName().equals(name))) {
            return "A playlist with the same name already exists.";
        }
        playlists.add(new Playlist(name, username, timestamp));

        return "Playlist created successfully.";
    }

    /**
     *
     * @param id the ID of the song to be added or removed
     * @return the result of the add/remove operation
     */
    public String addRemoveInPlaylist(final int id) {
        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before adding to or removing from the playlist.";
        }
        if (player.getType().equals("podcast")) {
            return "The loaded source is not a song.";
        }
        if (id > playlists.size()) {
            return "The specified playlist does not exist.";
        }
        Playlist playlist = playlists.get(id - 1);

        if (playlist.containsSong((Song) player.getCurrentAudioFile())) {
            playlist.removeSong((Song) player.getCurrentAudioFile());
            return "Successfully removed from playlist.";
        }

        playlist.addSong((Song) player.getCurrentAudioFile());
        return "Successfully added to playlist.";
    }

    /**
     *
     * @param playlistId the ID of the playlist to be switched
     * @return the result of the switch operation
     */
    public String switchPlaylistVisibility(final Integer playlistId) {
        if (playlistId > playlists.size()) {
            return "The specified playlist ID is too high.";
        }
        Playlist playlist = playlists.get(playlistId - 1);
        playlist.switchVisibility();

        if (playlist.getVisibility() == Enums.Visibility.PUBLIC) {
            return "Visibility status updated successfully to public.";
        }

        return "Visibility status updated successfully to private.";
    }
/**
     *
     * @return the result of the showPlaylists operation
 */
    public ArrayList<PlaylistOutput> showPlaylists() {
        ArrayList<PlaylistOutput> playlistOutputs = new ArrayList<>();
        for (Playlist playlist : playlists) {
            playlistOutputs.add(new PlaylistOutput(playlist));
        }

        return playlistOutputs;
    }

    /**
     *
     * @return the result of the follow operation
     */
    public String follow() {
        LibraryEntry selection = searchBar.getLastSelected();
        String type1 = searchBar.getLastSearchType();

        if (selection == null) {
            return "Please select a source before following or unfollowing.";
        }
        if (!type1.equals("playlist")) {
            return "The selected source is not a playlist.";
        }
        Playlist playlist = (Playlist) selection;

        if (playlist.getOwner().equals(username)) {
            return "You cannot follow or unfollow your own playlist.";
        }
        if (followedPlaylists.contains(playlist)) {
            followedPlaylists.remove(playlist);
            playlist.decreaseFollowers();

            return "Playlist unfollowed successfully.";
        }

        followedPlaylists.add(playlist);
        playlist.increaseFollowers();


        return "Playlist followed successfully.";
    }

    /**
     *
     * @return player stats
     */
    public PlayerStats getPlayerStats() {
        return player.getStats();
    }

    /**
     *
     * @return the user's preferred songs
     */
    public ArrayList<String> showPreferredSongs() {
        ArrayList<String> results = new ArrayList<>();
        for (AudioFile audioFile : likedSongs) {
            results.add(audioFile.getName());
        }

        return results;
    }

    /**
     *
     * @return the user's preferred genre
     */
    public String getPreferredGenre() {
        String[] genres = {"pop", "rock", "rap"};
        int[] counts = new int[genres.length];
        int mostLikedIndex = -1;
        int mostLikedCount = 0;

        for (Song song : likedSongs) {
            for (int i = 0; i < genres.length; i++) {
                if (song.getGenre().equals(genres[i])) {
                    counts[i]++;
                    if (counts[i] > mostLikedCount) {
                        mostLikedCount = counts[i];
                        mostLikedIndex = i;
                    }
                    break;
                }
            }
        }

        String preferredGenre = mostLikedIndex != -1 ? genres[mostLikedIndex] : "unknown";
        return "This user's preferred genre is %s.".formatted(preferredGenre);
    }

    /**
     *
     * @param time the time to be simulated
     */
    public void simulateTime(final int time) {
        if (!isOnline) {
            return;
        }
        player.simulatePlayer(time);
    }

    /**
     *
     * @return the current page
     */
    public String printCurrentPage() {
        switch (page) {
            case "Home":
                String output = "Liked songs:\n\t[";
                output += printLikedSongs();
                output += "]\n\nFollowed playlists:\n\t[";
                output += printFollowedPlaylists();
                output += "]";
                currentEntityName = "";
                return output;
            case "LikedContent":
                String output1 = "Liked songs:\n\t[";
                output1 += printLikedAllData();
                output1 += "]";
                output1 += "\n\nFollowed playlists:\n\t[";
                output1 += printFollowedAllData();
                output1 += "]";
                currentEntityName = "";
                return output1;
            case "Artist":
                Artist artist = (Artist) Admin.getUser(getSearchBar().
                        getLastSelected().getName());
                currentEntityName = artist.getName();
                if (artist == null) {
                    return "Artist not found";
                }
                return artist.printArtist();
            case "Host":
                if (lastSelectedHost == null) {
                    return "Please select a host";
                }
                Host host = (Host) Admin.getUser(lastSelectedHost);
                currentEntityName = host.getName();
                if (host == null) {
                    return "Host not found";
                }
                return host.printHost();
            default:
                return "Invalid page";
        }
    }
    /**
     *
     * @return the liked songs
     */
    public String printLikedSongs() {
        List<Song> top5Songs = getTop5Songs();
        String res = "";
        for (Song song : top5Songs) {
            res += song.getName();
            if (!song.equals(top5Songs.get(top5Songs.size() - 1))) {
                res += ",";
            }
            res += " ";
        }
        if (res.length() > 0) {
            res = res.substring(0, res.length() - 1);
        }
        return res;
    }

     /**
     *
     * @return the followed playlists
     */
    public String printFollowedPlaylists() {
        String res = "";
        for (Playlist playlist : followedPlaylists) {
            res += playlist.getName() + " ";
        }
        if (followedPlaylists.size() > 0) {
            res = res.substring(0, res.length() - 1);
        }
        return res;

    }

    /**
     *
     * @return the liked songs (liked page)
     */
    public String printLikedAllData() {
        String res = "";
        for (Song song : likedSongs) {
            res += song.getName() + " - ";
            //check if the song is the last one in liked songs
            res += song.getArtist();
            if (!song.equals(likedSongs.get(likedSongs.size() - 1))) {
                res += ",";

            }
            res += " ";
        }
        if (likedSongs.size() > 0) {
            res = res.substring(0, res.length() - 1);
        }
        return res;
}

    /**
     *
     * @return the followed playlists (liked page)
     */
    public String printFollowedAllData() {
        String res = "";
        for (Playlist playlist : followedPlaylists) {
            res += playlist.getName() + " - ";
            res += playlist.getOwner() + " ";
        }
        if (followedPlaylists.size() > 0) {
            res = res.substring(0, res.length() - 1);
        }
        return res;
}
    /**
     *
     * @param name the name to match
     * @return whether the name matches or not
     */
    public  boolean matchesName(final String name) {
        if (this.username.startsWith(name)) {
            return true;
        }
        return false;
    }

    /**
     *
     * @return the top 5 songs of the user
     */
    public List<Song> getTop5Songs() {
        List<Song> top5Songs = new ArrayList<>(likedSongs);
        top5Songs.sort(Comparator.comparingInt(Song::getLikes).reversed());
        if (top5Songs.size() > Admin.getNoOfSearchResults()) {
            top5Songs = top5Songs.subList(0,
                    Admin.getNoOfSearchResults());
        }
        return top5Songs;
    }
}
