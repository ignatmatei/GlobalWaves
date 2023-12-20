package app.authorizers;

public interface Authorizer {

    /**
     *
     * @param username the username to be checked
     * @return true if the user can be deleted, false otherwise
     */
     boolean canIDeleteUser(String username);
    /**
     *
     * @param albumName the album name to be checked
     * @return true if the album can be deleted, false otherwise
     */
      boolean canIDeleteAlbum(String albumName);

    /**
     *
     * @param podcastName the podcast name to be checked
     * @return true if the podcast can be deleted, false otherwise
     */
    boolean canIDeletePodcast(String podcastName);
}
