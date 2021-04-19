import java.util.ArrayList;

public class VideoController {

    private static ArrayList<Video> videosList;

    /**
     * Get a {@link ArrayList} containing all videos within it.
     * @return an {@link ArrayList} containing {@link Video}
     */
    public static ArrayList<Video> getVideosList(){
        
        return null;
    }

    /**
     * <p>Get a video by its id.
     * <p>Return {@code null} if no video of this id was found.
     * @param id
     * @return {@link Video}
     */
    public static Video getVideoById(int id){
        return null;
    }

    /**
     * <p>Get a video list by its author.
     * <p>Return an empty list if no video of this id was found.
     * @param author {@link User} who upload this video
     * @return an {@link ArrayList} containing {@link Video} made by this author
     */
    public static ArrayList<Video> getVideosByAuthor(User author){
        return null;
    }

    /**
     * <p>Search for the keyword in title among all the videos. Rankings are in no particular order.
     * <p>If no video was found, return an empty list.
     * @param keyword to be search in title
     * @return
     */
    public static ArrayList<Video> searchVideosByTitle(String keyword){
        return null;
    }

    /**
     * <p>Remove a video.
     * <p>This method will not delete a file, just remove it from the 'videos.csv' and the list.
     * @param video {@link Video} to be remove
     * @return true if video was deleted
     */
    public static boolean removeVideo(Video video){
        return false;
    }
}
