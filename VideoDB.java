import java.util.ArrayList;
import java.util.Iterator;

/**
 * This is video database, storing data as video object list
 */
public class VideoDB {
   //private static final String basicPath = "D:\\Work Zone\\GitHub\\Digital_gym_system\\data\\video.csv";
   private static final String dataPath = "./data/video.csv";
   private static ArrayList<Video> videoList;

    /**
     * THis is function used by controller to get data
     * @return the list containing data
     */
    public static synchronized ArrayList<Video> getVideos(){
        if (videoList == null){
            initVideoList();
        } 
        return videoList;
    }

    /**
     * @description this function can either add new video or change the existing one to local,
     * just make sure the id of video is matched
     * @param video the new video obj you want set
     */
   public static synchronized void addVideo(Video video) {
        if (videoList == null){
            initVideoList();
        } 
        boolean exsited = videoList.removeIf(e ->e.getId()==video.getId());
        videoList.add(video);
        if(exsited){
            ArrayList<String> vList = new ArrayList<>();
            for(Video s : videoList){
                String data = s.getId()+","+s.getAuthor().getId()+","+
                s.getTitle()+","+s.getVideoPath()+","+s.getDescription();
                vList.add(data);
            }
            DataHandler.write(vList, dataPath);
        }
        else{
            String data = video.getId()+","+video.getAuthor().getId()+","+
            video.getTitle()+","+video.getVideoPath()+","+video.getDescription();
            DataHandler.append(data, dataPath);
        }

    }

    /**
     * @description this, is a function that will store data to memory from local file
     * private function
     */
    private static synchronized void initVideoList(){
        ArrayList<String> videos = DataHandler.read(dataPath);
        String line;
        String cvsSplitBy = ",";
        Iterator<String> iterator = videos.iterator();
        while (iterator.hasNext()) {
            line = (String) (iterator.next());
            String[] ele = line.split(cvsSplitBy);
            String description = ele[4];
            for(int i=5; i < ele.length; i++){
                description = description+ ","+ ele[i]; 
            }
            User author =UserController.getUserById(Integer.parseInt(ele[1]));
            Video data = new Video(Integer.parseInt(ele[0]), author, ele[2],ele[3], description);
            videoList.add(data);   
        }
    }


}
