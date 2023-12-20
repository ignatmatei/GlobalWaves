package app;

import app.audio.Collections.Playlist;
import app.audio.Collections.Album;
import app.audio.Collections.Podcast;
import app.audio.Collections.PlaylistOutput;
import app.audio.Files.Episode;
import app.audio.Files.Song;
import app.authorizers.DeleteAuthorizer;
import app.player.PlayerStats;
import app.searchBar.Filters;
import app.user.Artist;
import app.user.Host;
import app.user.User;
import app.user.UserFactory;
import app.user.Announcement;
import app.user.Merch;
import app.utils.AlbumOutput;
import app.utils.ObjectNodeBuilder;
import app.utils.PodcastOutput;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.input.CommandInput;
import fileio.input.UserInput;

import java.util.ArrayList;
import java.util.List;

public final class CommandRunner {
    private static ObjectMapper objectMapper = new ObjectMapper();
    /**
     * constructor
     */
    private CommandRunner() {

    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    /**
     *
     * @param commandInput the command input
     * @return search command result
     */
    public static ObjectNode search(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        ObjectNode objectNode = ObjectNodeBuilder.buildObjectNode(commandInput.getCommand(),
                commandInput.getUsername(), commandInput.getTimestamp());
        if (!user.isOnline()) {
            objectNode.put("message", commandInput.getUsername() + " is offline.");
            objectNode.put("results", objectMapper.valueToTree(new ArrayList<>()));
            return objectNode;
        }
        Filters filters = new Filters(commandInput.getFilters());
        String type = commandInput.getType();
            ArrayList<String> results = user.search(filters, type);
            String message = "Search returned " + results.size() + " results";
            objectNode.put("message", message);
            objectNode.put("results", objectMapper.valueToTree(results));
            return objectNode;

    }

    /**
     *
     * @param commandInput the command input
     * @return select command result
     */
    public static ObjectNode select(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        ObjectNode objectNode = ObjectNodeBuilder.buildObjectNode(commandInput.getCommand(),
                commandInput.getUsername(), commandInput.getTimestamp());
        if (!user.isOnline()) {
            objectNode.put("message", commandInput.getUsername() + " is offline.");
            return objectNode;
        }
        String message = user.select(commandInput.getItemNumber());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     *
     * @param commandInput the command input
     * @return load command result
     */
    public static ObjectNode load(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        ObjectNode objectNode = ObjectNodeBuilder.buildObjectNode(commandInput.getCommand(),
                commandInput.getUsername(), commandInput.getTimestamp());
        if (!user.isOnline()) {
            objectNode.put("message", commandInput.getUsername() + " is offline.");
            return objectNode;
        }
        String message = user.load();
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     *
     * @param commandInput the command input
     * @return playPause command result
     */
    public static ObjectNode playPause(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        String message = user.playPause();
        ObjectNode objectNode = ObjectNodeBuilder.buildObjectNode(commandInput.getCommand(),
                commandInput.getUsername(), commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     *
     * @param commandInput the command input
     * @return repeat command result
     */
    public static ObjectNode repeat(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        String message = user.repeat();
        ObjectNode objectNode = ObjectNodeBuilder.buildObjectNode(commandInput.getCommand(),
                commandInput.getUsername(), commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     *
     * @param commandInput the command input
     * @return shuffle command result
     */
    public static ObjectNode shuffle(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        Integer seed = commandInput.getSeed();
        String message = user.shuffle(seed);
        ObjectNode objectNode = ObjectNodeBuilder.buildObjectNode(commandInput.getCommand(),
                commandInput.getUsername(), commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     *
     * @param commandInput the command input
     * @return forward command result
     */
    public static ObjectNode forward(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        String message = user.forward();
        ObjectNode objectNode = ObjectNodeBuilder.buildObjectNode(commandInput.getCommand(),
                commandInput.getUsername(), commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     *
     * @param commandInput the command input
     * @return backward command result
     */
    public static ObjectNode backward(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        String message = user.backward();
        ObjectNode objectNode = ObjectNodeBuilder.buildObjectNode(commandInput.getCommand(),
                commandInput.getUsername(), commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     *
     * @param commandInput the command input
     * @return like command result
     */

    public static ObjectNode like(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        ObjectNode objectNode = ObjectNodeBuilder.buildObjectNode(commandInput.getCommand(),
                commandInput.getUsername(), commandInput.getTimestamp());
        if (!user.isOnline()) {
            objectNode.put("message", commandInput.getUsername() + " is offline.");
            return objectNode;
        }
        String message = user.like();
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     *
     * @param commandInput the command input
     * @return next command result
     */
    public static ObjectNode next(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        String message = user.next();
        ObjectNode objectNode = ObjectNodeBuilder.buildObjectNode(commandInput.getCommand(),
                commandInput.getUsername(), commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     *
     * @param commandInput the command input
     * @return prev command result
     */
    public static ObjectNode prev(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        String message = user.prev();
        ObjectNode objectNode = ObjectNodeBuilder.buildObjectNode(commandInput.getCommand(),
                commandInput.getUsername(), commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     *
     * @param commandInput the command input
     * @return createPlaylist command result
     */
    public static ObjectNode createPlaylist(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        String message =
                user.createPlaylist(commandInput.getPlaylistName(), commandInput.getTimestamp());
        ObjectNode objectNode = ObjectNodeBuilder.buildObjectNode(commandInput.getCommand(),
                commandInput.getUsername(), commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     *
     * @param commandInput the command input
     * @return addrRemoveinPlaylist command result
     */
    public static ObjectNode addRemoveInPlaylist(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        String message = user.addRemoveInPlaylist(commandInput.getPlaylistId());
        ObjectNode objectNode = ObjectNodeBuilder.buildObjectNode(commandInput.getCommand(),
                commandInput.getUsername(), commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     *
     * @param commandInput the command input
     * @return switchVisibility command result
     */
    public static ObjectNode switchVisibility(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        String message = user.switchPlaylistVisibility(commandInput.getPlaylistId());
        ObjectNode objectNode = ObjectNodeBuilder.buildObjectNode(commandInput.getCommand(),
                commandInput.getUsername(), commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     *
     * @param commandInput the command input
     * @return showPlaylists command result
     */
    public static ObjectNode showPlaylists(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        ArrayList<PlaylistOutput> playlists = user.showPlaylists();
        ObjectNode objectNode = ObjectNodeBuilder.buildObjectNode(commandInput.getCommand(),
                commandInput.getUsername(), commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(playlists));

        return objectNode;
    }

    /**
     *
     * @param commandInput the command input
     * @return follow command result
     */
    public static ObjectNode follow(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        String message = user.follow();
        ObjectNode objectNode = ObjectNodeBuilder.buildObjectNode(commandInput.getCommand(),
                commandInput.getUsername(), commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     *
     * @param commandInput the command input
     * @return status command result
     */
    public static ObjectNode status(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        PlayerStats stats = user.getPlayerStats();
        ObjectNode objectNode = ObjectNodeBuilder.buildObjectNode(commandInput.getCommand(),
                commandInput.getUsername(), commandInput.getTimestamp());
        objectNode.put("stats", objectMapper.valueToTree(stats));

        return objectNode;
    }

    /**
     *
     * @param commandInput the command input
     * @return showLikedSongs command result
     */
    public static ObjectNode showLikedSongs(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        ArrayList<String> songs = user.showPreferredSongs();
        ObjectNode objectNode = ObjectNodeBuilder.buildObjectNode(commandInput.getCommand(),
                commandInput.getUsername(), commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(songs));

        return objectNode;
    }

    /**
     *
     * @param commandInput the command input
     * @return getPreferredGenre command result
     */
    public static ObjectNode getPreferredGenre(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        String preferredGenre = user.getPreferredGenre();
        ObjectNode objectNode = ObjectNodeBuilder.buildObjectNode(commandInput.getCommand(),
                commandInput.getUsername(), commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(preferredGenre));
        return objectNode;
    }

    /**
     *
     * @param commandInput the command input
     * @return getTop5Songs command result
     */
    public static ObjectNode getTop5Songs(final CommandInput commandInput) {
        List<String> songs = Admin.getTop5Songs();
        ObjectNode objectNode = ObjectNodeBuilder.buildObjectNode(commandInput.getCommand(),
                commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(songs));
        return objectNode;
    }
    /**
     *
     * @param commandInput the command input
     * @return getTop5Albums command result
     */
    public static ObjectNode getTop5Albums(final CommandInput commandInput) {
        List<String> albums = Admin.getTop5Albums();
        ObjectNode objectNode = ObjectNodeBuilder.buildObjectNode(commandInput.getCommand(),
                commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(albums));
        return objectNode;
    }

    /**
     *
     * @param commandInput the command input
     * @return getTop5Playlists command result
     */
    public static ObjectNode getTop5Playlists(final CommandInput commandInput) {
        List<String> playlists = Admin.getTop5Playlists();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(playlists));

        return objectNode;
    }

    /**
     *
     * @param commandInput the command input
     * @return getTop5Artists command result
     */
    public static ObjectNode getTop5Artists(final CommandInput commandInput) {
        List<String> artists = Admin.getTop5Artists();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(artists));

        return objectNode;
    }
    /**
     *
     * @param commandInput the command input
     * @return addUser command result
     */
    public static ObjectNode addUser(final CommandInput commandInput) {
        ObjectNode objectNode = ObjectNodeBuilder.buildObjectNode(commandInput.getCommand(),
                commandInput.getUsername(), commandInput.getTimestamp());
        for (User user : Admin.getUsers()) {
            if (user.getUsername().equals(commandInput.getUsername())) {
                objectNode.put("message",
                        "The username " + commandInput.getUsername() + " is already taken.");
                return objectNode;
            }
        }
        UserInput userInput = new UserInput();
        userInput.setAge(Integer.parseInt(commandInput.getAge()));
        userInput.setCity(commandInput.getCity());
        userInput.setUsername(commandInput.getUsername());
        UserFactory userFactory = UserFactory.getInstance();
        objectNode.put("message", userFactory.createUser(userInput, commandInput.getType()));
        return objectNode;
    }

    /**
     *
     * @param commandInput the command input
     * @return deleteUser command result
     */
    public static ObjectNode deleteUser(final CommandInput commandInput) {
        ObjectNode objectNode = ObjectNodeBuilder.buildObjectNode(commandInput.getCommand(),
                commandInput.getUsername(), commandInput.getTimestamp());
        DeleteAuthorizer deleteAuthorizer =
                DeleteAuthorizer.getInstance();
        for (User user : Admin.getUsers()) {
            if (user.getUsername().equals(commandInput.getUsername())) {
                 if (deleteAuthorizer.
                         canIDeleteUser(commandInput.getUsername())) {
                     if (user.getType().equals("artist")) {
                        for (Song song : Admin.getSongs()) {
                            if (song.getArtist().equals(user.getUsername())) {
                                Admin.removeSong(song);
                            }
                        }
                     }
                     for (Playlist playlist : Admin.getPlaylists()) {
                         if (playlist.getOwner().equals(user.getUsername())) {
                             Admin.removePlaylist(playlist);
                         }
                     }
                     Admin.removeAFollow(user);
                     Admin.getUsers().remove(user);
                 } else {
                        objectNode.put("message", commandInput.getUsername()
                                +
                                " can't be deleted.");
                        return objectNode;
                 }
                objectNode.put("message", commandInput.getUsername()
                        +
                        " was successfully deleted.");
                return objectNode;
            }
        }
        objectNode.put("message", "User does not exist.");
        return objectNode;
    }
    /**
     *
     * @param commandInput the command input
     * @return changeConnectionStatus command result
     */
    public static ObjectNode switchConnectionStatus(final CommandInput commandInput) {
        ObjectNode objectNode = ObjectNodeBuilder.buildObjectNode(commandInput.getCommand(),
                commandInput.getUsername(), commandInput.getTimestamp());
        User user = Admin.getUser(commandInput.getUsername());
        if (user == null) {
            objectNode.put("message", "The username "
                    +
                    commandInput.getUsername() + " doesn't exist.");
            return objectNode;
        }
        if (user.getType().equals("artist") || user.getType().equals("host")) {
            objectNode.put("message", commandInput.getUsername() + " is not a normal user.");
            return objectNode;
        }
        user.setOnline(!user.isOnline());
        objectNode.put("message", commandInput.getUsername() + " has changed status successfully.");
        return objectNode;
    }

    /**
     *
     * @param commandInput the command input
     * @return getOnlineUsers command result
     */
    public static ObjectNode getOnlineUsers(final CommandInput commandInput) {
        ObjectNode objectNode = ObjectNodeBuilder.buildObjectNode(commandInput.getCommand(),
                commandInput.getTimestamp());
        ArrayList<String> onlineUsers = new ArrayList<>();
        for (User user : Admin.getUsers()) {
            if (user.isOnline() && user.getType().equals("user")) {
                onlineUsers.add(user.getUsername());
            }
        }
        objectNode.put("result", objectMapper.valueToTree(onlineUsers));
        return objectNode;
    }

    /**
     *
     * @param commandInput the command input
     * @return addAlbum command result
     */
    public static ObjectNode addAlbum(final CommandInput commandInput) {
        ObjectNode objectNode = ObjectNodeBuilder.buildObjectNode(commandInput.getCommand(),
                commandInput.getUsername(), commandInput.getTimestamp());
        if (Admin.getUser(commandInput.getUsername()) == null) {
            objectNode.put("message", commandInput.getUsername() + " does not exist.");
            return objectNode;
        }
        User u = Admin.getUser(commandInput.getUsername());
        if (!u.getType().equals("artist")) {
            objectNode.put("message", commandInput.getUsername() + " is not an artist.");
            return objectNode;
        }
        Artist artist = (Artist) u;
        for (int i = 0; i < artist.getAlbums().size(); i++) {
            if (artist.getAlbums().get(i).getName().equals(commandInput.getName())) {
                objectNode.put("message",
                        commandInput.getUsername() + " has another album with the same name.");
                return objectNode;
            }
        }
        if (duplicatedSong(commandInput.getSongs())) {
            objectNode.put("message",
                    u.getUsername() + " has the same song at least twice in this album.");
            return objectNode;
        }
         List<String> songNames = new ArrayList<>();
        for (Song song : commandInput.getSongs()) {
            songNames.add(song.getName());
        }
        for (Song song : commandInput.getSongs()) {
            if (!Admin.getSongs().contains(song)) {
                Admin.addSong(song);
            }
        }
        Album album = new Album(commandInput.getName(), commandInput.getSongs(),
                commandInput.getUsername());
        artist.addAlbum(album);
        Admin.addAlbum(album);
        objectNode.put("message",
                commandInput.getUsername() + " has added new album successfully.");
        return objectNode;
    }

    /**
     *
     * @param commandInput the command input
     * @return removeAlbum command result
     */
 public static ObjectNode removeAlbum(final CommandInput commandInput) {
     ObjectNode objectNode = ObjectNodeBuilder.buildObjectNode(commandInput.getCommand(),
             commandInput.getUsername(), commandInput.getTimestamp());
        User u = Admin.getUser(commandInput.getUsername());
        DeleteAuthorizer deleteAuthorizer =
                DeleteAuthorizer.getInstance();
        if (u == null) {
            objectNode.put("message", "The username "
                    +
                    commandInput.getUsername() + " doesn't exist.");
            return objectNode;
        }
        if (!u.getType().equals("artist")) {
            objectNode.put("message", commandInput.getUsername() + " is not an artist.");
            return objectNode;
        }
        Artist artist = (Artist) u;
        for (int i = 0; i < artist.getAlbums().size(); i++) {
            if (artist.getAlbums().get(i).getName().equals(commandInput.getName())) {
                if (deleteAuthorizer.
                        canIDeleteAlbum(commandInput.getName())) {
                    artist.getAlbums().remove(i);
                    objectNode.put("message",
                            commandInput.getUsername() + " has removed the album successfully.");
                    return objectNode;
                } else {
                    objectNode.put("message",
                            commandInput.getUsername() + " can't delete this album.");
                    return objectNode;
                }
            }
        }
        objectNode.put("message",
                commandInput.getUsername() + " doesn't have an album with the given name.");
        return objectNode;
    }
    /**
     *
     * @param songs the songs to be checked
     * @return true if there are duplicated songs, false otherwise
     */
    public static boolean duplicatedSong(final List<Song> songs) {
        for (int i = 0; i < songs.size(); i++) {
            for (int j = i + 1; j < songs.size(); j++) {
                if (songs.get(i).getName().equals(songs.get(j).getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *
     * @param commandInput the command input
     * @return getAlbums command result
     */
    public static ObjectNode showAlbums(final CommandInput commandInput) {
        ObjectNode objectNode = ObjectNodeBuilder.buildObjectNode(commandInput.getCommand(),
                commandInput.getUsername(), commandInput.getTimestamp());
        Artist u = (Artist) Admin.getUser(commandInput.getUsername());
        List<AlbumOutput> result = new ArrayList<>();
        for (Album album : u.getAlbums()) {
            result.add(new AlbumOutput(album.getName(), album.getSongsNames()));
        }
        objectNode.put("result", objectMapper.valueToTree(result));
         return objectNode;
    }

    /**
     *
     * @param commandInput the command input
     * @return showPodcasts command result
     */
    public static ObjectNode showPodcasts(final CommandInput commandInput) {
        ObjectNode objectNode = ObjectNodeBuilder.buildObjectNode(commandInput.getCommand(),
                commandInput.getUsername(), commandInput.getTimestamp());
        Host host = (Host) Admin.getUser(commandInput.getUsername());
        List<PodcastOutput> result = new ArrayList<>();
        for (Podcast podcast : host.getPodcasts()) {
            result.add(new PodcastOutput(podcast.getName(), podcast.getEpisodesNames()));
        }
        objectNode.put("result", objectMapper.valueToTree(result));
        return objectNode;
    }
    /**
     *
     * @param commandInput
     * @return
     */
    public static ObjectNode addMerch(final CommandInput commandInput) {
        ObjectNode objectNode = ObjectNodeBuilder.buildObjectNode(commandInput.getCommand(),
                commandInput.getUsername(), commandInput.getTimestamp());
        if (Admin.getUser(commandInput.getUsername()) == null) {
            objectNode.put("message", "The username "
                    +
                    commandInput.getUsername() + " doesn't exist.");
            return objectNode;
        }
        Artist u = (Artist) Admin.getUser(commandInput.getUsername());
        for (int i = 0; i < u.getMerch().size(); i++) {
            if (u.getMerch().get(i).getName().equals(commandInput.getName())) {
                objectNode.put("message",
                        commandInput.getUsername() + " has merchandise with the same name.");
                return objectNode;
            }
        }
        Merch merch = new Merch(commandInput.getName(), commandInput.getPrice(),
                commandInput.getDescription());
        if (merch.getPrice() < 0) {
            objectNode.put("message", "Price for merchandise can not be negative.");
            return objectNode;
        }
        u.addMerch(merch);
        objectNode.put("message",
                commandInput.getUsername() + " has added new merchandise successfully.");
        return objectNode;
    }

    /**
     *
     * @param commandInput the command input
     * @return removeMerch command result
     */
    public static ObjectNode removeMerch(final CommandInput commandInput) {
        ObjectNode objectNode = ObjectNodeBuilder.buildObjectNode(commandInput.getCommand(),
                commandInput.getName(), commandInput.getTimestamp());
        User user = Admin.getUser(commandInput.getUsername());
        if (user == null) {
            objectNode.put("message", "The username "
                    +
                    commandInput.getUsername() + " doesn't exist.");
            return objectNode;
        }
        if (!user.getType().equals("artist")) {
            objectNode.put("message", commandInput.getUsername() + " is not an artist.");
            return objectNode;
        }
        Artist artist = (Artist) user;
        for (int i = 0; i < artist.getMerch().size(); i++) {
            if (artist.getMerch().get(i).getName().equals(commandInput.getName())) {
                artist.getMerch().remove(i);
                objectNode.put("message",
                        commandInput.getUsername() + " has removed the merchandise successfully.");
                return objectNode;
            }
        }
        objectNode.put("message",
                commandInput.getUsername() + " does not have merchandise with the given name.");
        return objectNode;
    }

    /**
     *
     * @param commandInput the command input
     * @return getMerch command result
     */
    public static ObjectNode addEvent(final CommandInput commandInput) {
        ObjectNode objectNode = ObjectNodeBuilder.buildObjectNode(commandInput.getCommand(),
                commandInput.getUsername(), commandInput.getTimestamp());
        if (Admin.getUser(commandInput.getUsername()) == null) {
            objectNode.put("message", "The username "
                    +
                    commandInput.getUsername() + " doesn't exist.");
            return objectNode;
        }
        User u = Admin.getUser(commandInput.getUsername());
        if (!u.getType().equals("artist")) {
            objectNode.put("message", commandInput.getUsername() + " is not an artist.");
            return objectNode;
        }
        Artist artist = (Artist) u;
        for (int i = 0; i < artist.getEvents().size(); i++) {
            if (artist.getEvents().get(i).getName().equals(commandInput.getName())) {
                objectNode.put("message",
                        commandInput.getUsername() + " has another event with the same name.");
                return objectNode;
            }
        }
        if (!validDate(commandInput.getDate())) {
            objectNode.put("message", "Event for "
            + commandInput.getUsername() + " does not have a valid date.");
            return objectNode;
        }
        artist.addEvent(new app.user.Event(commandInput.getName(), commandInput.getDate(),
        commandInput.getDescription()));
        objectNode.put("message",
                commandInput.getUsername() + " has added new event successfully.");
        return objectNode;
    }

    /**
     * checks if the date is valid
     * @param date the date to be checked
     * @return true if the date is valid, false otherwise
     */
    public static boolean validDate(final String date) {
        String[] dateParts = date.split("-");
        int day = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int year = Integer.parseInt(dateParts[2]);
        if (year < 1900 || year > 2023) {
            return false;
        }
        if (month > 12) {
            return false;
        }
        if (day > 28 && month == 2) {
            return false;
        }
        if (day > 31) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param commandInput the command input
     * @return removeEvent command result
     */
    public static ObjectNode removeEvent(final CommandInput commandInput) {
        ObjectNode objectNode = ObjectNodeBuilder.buildObjectNode(commandInput.getCommand(),
                commandInput.getUsername(), commandInput.getTimestamp());
        User u = Admin.getUser(commandInput.getUsername());
        if (u == null) {
            objectNode.put("message", "The username "
                    +
                    commandInput.getUsername() + " doesn't exist.");
            return objectNode;
        }
        if (!u.getType().equals("artist")) {
            objectNode.put("message", commandInput.getUsername() + " is not an artist.");
            return objectNode;
        }
        Artist artist = (Artist) u;
        for (int i = 0; i < artist.getEvents().size(); i++) {
            if (artist.getEvents().get(i).getName().equals(commandInput.getName())) {
                artist.getEvents().remove(i);
                objectNode.put("message",
                        commandInput.getUsername() + " deleted the event successfully.");
                return objectNode;
            }
        }
        objectNode.put("message",
                commandInput.getUsername() + " does not have an event with the given name.");
        return objectNode;
    }
    /**
     *
     * @param commandInput the command input
     * @return addPodcast command result
     */
    public static ObjectNode addPodcast(final CommandInput commandInput) {
        ObjectNode objectNode = ObjectNodeBuilder.buildObjectNode(commandInput.getCommand(),
                commandInput.getUsername(), commandInput.getTimestamp());
        if (Admin.getUser(commandInput.getUsername()) == null) {
            objectNode.put("message", "The username "
                    +
                    commandInput.getUsername() + " doesn't exist.");
            return objectNode;
        }
        User u = Admin.getUser(commandInput.getUsername());
        if (!u.getType().equals("host")) {
            objectNode.put("message", commandInput.getUsername() + " is not a host.");
            return objectNode;
        }
        Host host = (Host) u;
        for (int i = 0; i < host.getPodcasts().size(); i++) {
            if (host.getPodcasts().get(i).getName().equals(commandInput.getName())) {
                objectNode.put("message",
                        commandInput.getUsername() + " has another podcast with the same name.");
                return objectNode;
            }
        }
        if (duplicateEpisode(commandInput.getEpisodes())) {
            objectNode.put("message",
                    u.getUsername() + " has the same episode at least twice in this podcast.");
            return objectNode;
        }
        List<Episode> episodes = new ArrayList<>();
        for (Episode episode : commandInput.getEpisodes()) {
            episodes.add(new Episode(episode.getName(), episode.getDuration(),
                    episode.getDescription()));
        }
        Podcast podcast = new Podcast(commandInput.getName(), commandInput.getUsername(),
                episodes);
        host.addPodcast(podcast);
        objectNode.put("message",
                commandInput.getUsername() + " has added new podcast successfully.");
        Admin.addPodcast(podcast);
        return objectNode;
    }
    /**
     *
     * @param episodes the episodes to be checked
     * @return true if there are duplicated episodes, false otherwise
     */
    public static boolean duplicateEpisode(final List<Episode> episodes) {
        for (int i = 0; i < episodes.size(); i++) {
            for (int j = i + 1; j < episodes.size(); j++) {
                if (episodes.get(i).getName().equals(episodes.get(j).getName())) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     *
     * @param commandInput the command input
     * @return removePodcast command result
     */
    public static ObjectNode removePodcast(final CommandInput commandInput) {
        ObjectNode objectNode = ObjectNodeBuilder.buildObjectNode(commandInput.getCommand(),
                commandInput.getUsername(), commandInput.getTimestamp());
        DeleteAuthorizer deleteAuthorizer =
                DeleteAuthorizer.getInstance();
        User u = Admin.getUser(commandInput.getUsername());
        if (u == null) {
            objectNode.put("message", "The username "
                    +
                    commandInput.getUsername() + " doesn't exist.");
            return objectNode;
        }
        if (!u.getType().equals("host")) {
            objectNode.put("message", commandInput.getUsername() + " is not a host.");
            return objectNode;
        }
        Host host = (Host) u;
        for (int i = 0; i < host.getPodcasts().size(); i++) {
            if (host.getPodcasts().get(i).getName().equals(commandInput.getName())) {
                if (deleteAuthorizer.
                        canIDeletePodcast(commandInput.getName())) {
                    host.getPodcasts().remove(i);
                    objectNode.put("message",
                            commandInput.getUsername() + " deleted the podcast successfully.");
                    return objectNode;
                } else {
                    objectNode.put("message",
                            commandInput.getUsername() + " can't delete this podcast.");
                    return objectNode;
                }
            }
        }
        objectNode.put("message",
                commandInput.getUsername() + " doesn't have a podcast with the given name.");
        return objectNode;
    }

    /**
     *
     * @param commandInput the command input
     * @return addAnnouncement command result
     */
    public static ObjectNode addAnnouncement(final CommandInput commandInput) {
        ObjectNode objectNode = ObjectNodeBuilder.buildObjectNode(commandInput.getCommand(),
                commandInput.getUsername(), commandInput.getTimestamp());
        User u = Admin.getUser(commandInput.getUsername());
        if (u == null) {
            objectNode.put("message", "The username "
                    +
                    commandInput.getUsername() + " doesn't exist.");
            return objectNode;
        }
        if (!u.getType().equals("host")) {
            objectNode.put("message", commandInput.getUsername() + " is not a host.");
            return objectNode;
        }
        Host host = (Host) u;
        for (int i = 0; i < host.getAnnouncements().size(); i++) {
            if (host.getAnnouncements().get(i).equals(commandInput.getName())) {
                objectNode.put("message",
                        commandInput.getUsername()
                                +
                                " has another announcement with the same name.");
                return objectNode;
            }
        }
        objectNode.put("message",
                commandInput.getUsername() + " has successfully added new announcement.");
        Announcement announcement = new Announcement(commandInput.getName(),
                commandInput.getDescription());
        host.addAnnouncement(announcement);
        return objectNode;
    }
    /**
     *
     * @param commandInput the command input
     * @return removeAnnouncement command result
     */
    public static ObjectNode removeAnnouncement(final CommandInput commandInput) {
        ObjectNode objectNode = ObjectNodeBuilder.buildObjectNode(commandInput.getCommand(),
                commandInput.getUsername(), commandInput.getTimestamp());
        User u = Admin.getUser(commandInput.getUsername());
        if (u == null) {
            objectNode.put("message", "The username "
                    +
                    commandInput.getUsername() + " doesn't exist.");
            return objectNode;
        }
        if (!u.getType().equals("host")) {
            objectNode.put("message", commandInput.getUsername() + " is not a host.");
            return objectNode;
        }
        Host host = (Host) u;
        for (int i = 0; i < host.getAnnouncements().size(); i++) {
            if (host.getAnnouncements().get(i).getName()
                    .equals(commandInput.getName())) {
                host.getAnnouncements().remove(i);
                objectNode.put("message",
                        commandInput.getUsername() + " has successfully"
                                 +
                                " deleted the announcement.");
                return objectNode;
            }
        }
        objectNode.put("message",
                commandInput.getUsername() + " has no announcement with the given name.");
        return objectNode;
    }

    /**
     *
     * @param commandInput the command input
     * @return printCurrentPage command result
     */
    public static ObjectNode printCurrentPage(final CommandInput commandInput) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        User u = Admin.getUser(commandInput.getUsername());
        if (u == null) {
            objectNode.put("message", "The username "
                    +
                    commandInput.getUsername() + " doesn't exist.");
            return objectNode;
        }
        if (!u.getType().equals("user")) {
            objectNode.put("message", commandInput.getUsername() + " is not a normal user.");
            return objectNode;
        }
        if (!u.isOnline()) {
            objectNode.put("message", commandInput.getUsername() + " is offline.");
            return objectNode;
        }
        objectNode.put("message", u.printCurrentPage());
        return objectNode;
    }
    /**
     *
     * @param commandInput the command input
     * @return changePage command result
     */
    public static ObjectNode changePage(final CommandInput commandInput) {
        ObjectNode objectNode = ObjectNodeBuilder.buildObjectNode(
                commandInput.getCommand(), commandInput.getUsername(), commandInput.getTimestamp());
        User u = Admin.getUser(commandInput.getUsername());
        if (u == null) {
            objectNode.put("message", "The username "
                    +
                    commandInput.getUsername() + " doesn't exist.");
            return objectNode;
        }
        u.setPage(commandInput.getNextPage());
        objectNode.put("message", commandInput.getUsername() + " accessed "
                 +
                commandInput.getNextPage() + " successfully.");
        return objectNode;
    }
    /**
     *
     * @param commandInput the command input
     * @return getAllUsers command result
     */
    public static ObjectNode getAllUsers(final CommandInput commandInput) {
        ObjectNode objectNode = ObjectNodeBuilder.buildObjectNode(
                commandInput.getCommand(), commandInput.getTimestamp());
        List<String> users = new ArrayList<>();
        for (User user : Admin.getUsers()) {
            if (user.getType().equals("user")) {
                users.add(user.getUsername());
            }
        }
        for (Artist artist : Admin.getArtists()) {
            users.add(artist.getUsername());
        }
        for (Host host : Admin.getHosts()) {
            users.add(host.getUsername());
        }
        objectNode.put("result", objectMapper.valueToTree(users));
        return objectNode;
    }
}
