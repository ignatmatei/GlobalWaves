package app.authorizers;

import app.Admin;
import app.audio.Collections.AudioCollection;
import app.audio.Collections.Podcast;
import app.audio.Files.Episode;
import app.user.Artist;
import app.user.Host;
import app.user.User;
import java.util.ArrayList;
import java.util.List;

public final class DeleteAuthorizer implements Authorizer {

    private static DeleteAuthorizer instance = null;
    /**
     * for coding style
     */
    private DeleteAuthorizer() { }

    /**
     * Singleton pattern
     * @return the instance of the class
     */
    public static DeleteAuthorizer getInstance() {
        if (instance == null) {
            instance = new DeleteAuthorizer();
        }
        return instance;
    }
    /**
     *
     * @param username the username to be checked
     * @return true if the user can be deleted, false otherwise
     */
    public boolean canIDeleteUser(final String username) {
        User user1 = Admin.getUser(username);
        for (User user : Admin.getUsers()) {
            if (user.getCurrentEntityName()
                    .equals(username)) {
                return false;
            }
        }
        if (user1.getType().equals("user")) {
            for (User user : Admin.getUsers()) {
                if (user.getPlayer().getSource() != null
                        && user.getPlayer().getSource().
                        getAudioCollection() != null) {
                    AudioCollection audioCollection = user.getPlayer().
                            getSource().getAudioCollection();
                    if (audioCollection.matchesOwner(username)) {
                        return false;
                    }
                }
            }
            return true;
        }
        if (user1.getType().equals("artist")) {
            Artist artist = (Artist) Admin.getUser(username);
            for (User user : Admin.getUsers()) {
                if (user.getPlayer()
                        .getCurrentAudioFile() != null) {
                    if (user.getPlayer().getCurrentAudioFile()
                            .matchesArtist(artist.getUsername())) {
                        return false;
                    }
                    if (user.getPlayer().getSource().getAudioCollection() != null) {
                        AudioCollection audioCollection = user.getPlayer().
                                getSource().getAudioCollection();
                        for (int i = 0; i < audioCollection.getNumberOfTracks(); i++) {
                            if (audioCollection.getTrackByIndex(i).
                                    matchesArtist(artist.getUsername())) {
                                return false;
                            }
                        }
                    }
                }
            }
            return true;
        }
        Host host = (Host) Admin.getUser(user1.getUsername());
        for (User user : Admin.getUsers()) {
            if (user.getPlayer()
                    .getCurrentAudioFile() != null) {
                if (user.getPlayer().getCurrentAudioFile()
                        .matchesOwner(host.getUsername())) {
                    return false;
                }
                if (user.getPlayer().getSource().getAudioCollection() != null) {
                    AudioCollection audioCollection = user.getPlayer().
                            getSource().getAudioCollection();
                    if (audioCollection.matchesOwner(host.getUsername())) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     *
     * @param albumName the album name to be checked
     * @return true if the album can be deleted, false otherwise
     */
    public boolean canIDeleteAlbum(final String albumName) {
        for (User user : Admin.getUsers()) {
            if (user.getPlayer()
                    .getCurrentAudioFile() != null) {
                if (user.getPlayer().getCurrentAudioFile().matchesAlbum(albumName)) {
                    return false;
                }
                if (user.getPlayer().getSource().getAudioCollection() != null) {
                    AudioCollection audioCollection =
                            user.getPlayer().getSource().getAudioCollection();
                    for (int i = 0; i < audioCollection.getNumberOfTracks(); i++) {
                        if (audioCollection.getTrackByIndex(i).matchesAlbum(albumName)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     *
     * @param podcastName the podcast name to be checked
     * @return true if the podcast can be deleted, false otherwise
     */
    public boolean canIDeletePodcast(final String podcastName) {
        Podcast podcast = Admin.getPodcast(podcastName);
        List<String> allPlayedEpisodes = new ArrayList<>();
        for (User user : Admin.getUsers()) {
            if (user.getType().equals("user") && user.getPlayer()
                    .getCurrentAudioFile() != null) {
                allPlayedEpisodes.add(user.getPlayer().
                        getCurrentAudioFile().getName());
            }
        }
        for (Episode episode : podcast.getEpisodes()) {
            if (allPlayedEpisodes.contains(episode.getName())) {
                return false;
            }
        }
        return true;
    }
}
