package app.audio.Collections;

import app.audio.Files.AudioFile;
import app.audio.Files.Episode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public final class Podcast extends AudioCollection {
    /**
     * -- GETTER --
     *
     */
    private final List<Episode> episodes;

    /**
     * Constructor
     */
    public Podcast(final String name, final String owner, final List<Episode> episodes) {
        super(name, owner);
        this.episodes = episodes;
    }
    /**
     *
     * @return the number of tracks in the collection
     */
    @Override
    public int getNumberOfTracks() {
        return episodes.size();
    }

    /**
     *
     * @param index the index of the track
     * @return the track at the given index
     */
    @Override
    public AudioFile getTrackByIndex(final int index) {
        return episodes.get(index);
    }
    /**
     *
     * @return the names of the episodes
     */
    public List<String> getEpisodesNames() {
        List<String> episodesNames = new ArrayList<>();
        for (Episode episode : episodes) {
            episodesNames.add(episode.getName());
        }
        return episodesNames;
    }
}
