package app.user;

import app.audio.Collections.Podcast;

import java.util.ArrayList;
import java.util.List;

public final class Host extends User {
    /**
     * Constructor
     */
    private List<Podcast> podcasts = new ArrayList<>();
    private List<Announcement> announcements = new ArrayList<>();
    public Host(final String username, final int age, final String city) {
        super(username, age, city);
    }
    /**
     * adds announcement
     * @param announcement the announcement to be added
     */
    public void addAnnouncement(final Announcement announcement) {
        announcements.add(announcement);
    }
    /**
     * removes announcement
     * @param announcement the announcement to be removed
     */
    public void removeAnnouncement(final Announcement announcement) {
        announcements.remove(announcement);
    }
    /**
     * adds podcast
     * @param podcast the podcast to be added
     */
    public void addPodcast(final Podcast podcast) {
        podcasts.add(podcast);
    }
    /**
     * removes podcast
     * @param podcast the podcast to be removed
     */
    public void removePodcast(final Podcast podcast) {
        podcasts.remove(podcast);
    }
    public List<Podcast> getPodcasts() {
        return podcasts;
    }
    public List<Announcement> getAnnouncements() {
        return announcements;
    }

    /**
     *
      * @return host's page
     */
    public String printHost() {
        String output = "Podcasts:\n\t[";
        for (Podcast podcast : podcasts) {
            output += podcast.getName() + ":\n\t[";
            for (int i = 0; i < podcast.getEpisodes().size(); i++) {
                output += podcast.getEpisodes().get(i).getName() + " - ";
                output += podcast.getEpisodes().get(i).getDescription() + ", ";
            }
            if (podcast.getEpisodes().size() > 0) {
                output = output.substring(0, output.length() - 2);
            }
            output += "]\n, ";
        }
        if  (podcasts.size() > 0) {
            output = output.substring(0, output.length() - 2);
        }
        output += "]";
        output += "\n\nAnnouncements:\n\t[";
        for (Announcement announcement : announcements) {
            output += announcement.getName() + ":\n\t"
                    +
                    announcement.getDescription() + "\n";
        }
        output += "]";
        return output;
    }
}
