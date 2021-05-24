import java.util.ArrayList;
import java.util.Iterator;

public class VideoController {

    private static final String dataPath = "./data/video.csv";

    /**
     * Get a {@link ArrayList} containing all videos within it.
     * @return an {@link ArrayList} containing {@link Video}
     */
    public static ArrayList<Video> getVideosList(){

        return new ArrayList<Video>(VideoDB.getVideos()); // Use this clone constructor
    }

    /**
     * <p>Get a video by its id.
     * <p>Return {@code null} if no video of this id was found.
     * @param id
     * @return {@link Video}
     */
    public static Video getVideoById(int id){
        for(Video s:VideoDB.getVideos()){
            if (s.getId()==id)
                return s;
        }
        return null;
    }

    /**
     * <p>Get a video list by its author.
     * <p>Return an empty list if no video of this id was found.
     * @param author {@link User} who upload this video
     * @return an {@link ArrayList} containing {@link Video} made by this author
     */
    public static ArrayList<Video> getVideosByAuthor(User author){
        ArrayList<Video> videosList=new ArrayList<>();
        for (Video s:VideoDB.getVideos()){
            if ((s.getAuthor().getId()== author.getId())) {
                videosList.add(s);
            }
        }
        return videosList;
    }

    /**
     * <p>Search for the keyword in title among all the videos. Rankings are in no particular order.
     * <p>If no video was found, return an empty list.
     * @param keyword to be search in title
     * @return
     */
    public static ArrayList<Video> searchVideosByTitle(String keyword){
        ArrayList<Video> videosList=new ArrayList<>();
        for (Video s:VideoDB.getVideos()){
            if (s.getTitle().contains(keyword))
                videosList.add(s);
        }
        return videosList;
    }

    public static void addVideo(User author, String title, String path, String description){
        VideoDB.addVideo(new Video(VideoDB.getMaxId() + 1, author, title, path, description));
    }


    /**
     * <p>Remove a video.
     * <p>This method will not delete a file, just remove it from the 'videos.csv' and the list.
     * @param video {@link Video} to be remove
     * @return true if video was deleted
     */
    public static boolean removeVideo(Video video){
        ArrayList<Video> videosList=new ArrayList<>();
        boolean flag=false;
        ArrayList<String> str=new ArrayList<>();
        videosList=VideoDB.getVideos();
        Iterator<Video> iterator=videosList.iterator();
        new CommentController(video).removeAll();
        while (iterator.hasNext()){
            Video vi=iterator.next();
            System.out.println(vi);
            if (getVideoById(video.getId())==getVideoById(vi.getId())){
                iterator.remove();
                flag=true;
            }
            else {
                String data = vi.getId()+","+vi.getAuthor().getId()+","+
                        vi.getTitle()+","+vi.getVideoPath()+","+vi.getDescription();
                str.add(data);
            }
        }
        if (flag){
            DataHandler.write(str,dataPath);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        addVideo(UserController.getUserById(6), "new", "meow", "aaaaaasssssdddd");
    }
}
