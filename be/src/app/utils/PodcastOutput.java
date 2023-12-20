package app.utils;

import java.util.List;

public final class PodcastOutput {
    private String name;
    private List<String> episodes;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<String> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(final List<String> episodes) {
        this.episodes = episodes;
    }

    public PodcastOutput(final String name, final List<String> episodes) {
        this.name = name;
        this.episodes = episodes;
    }
    public PodcastOutput() {
    }
}
