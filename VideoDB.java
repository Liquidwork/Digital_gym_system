import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * This is video database, storing data as video object list
 */
public class VideoDB {
   //private static final String dataPath = "D:\\Work Zone\\GitHub\\Digital_gym_system\\data\\video.csv";
   private static final String dataPath = "./data/video.csv";
   private static ArrayList<Video> videoList;
   private static HashMap<Video, Integer> viewMap;
   private static int maxId = 0;

    /**
     * THis is function used by controller to get data
     * @return the list containing data
     */
    public static synchronized ArrayList<Video> getVideos(){
        if (videoList == null){
            videoList = initVideoList();
        } 
        return videoList;
    }

    /**
     * @description this function can either add new video or change the existing one to local,
     * just make sure the id of video is matched
     * @param video the new video obj you want set
     */
   public static synchronized void addVideo(Video video) {
        if (videoList == null ){
            videoList = initVideoList();
        } 
        boolean exsited = videoList.removeIf(e ->e.getId()==video.getId());
        videoList.add(video);
        viewMap.put(video, 0);
        if(exsited){
            ArrayList<String> vList = new ArrayList<>();
            for(Video s : videoList){
                String data = s.getId()+","+s.getAuthor().getId()+","+
                s.getTitle()+","+s.getVideoPath()+"," + viewMap.get(s) + 
                ","+s.getDescription();
                vList.add(data);
            }
            DataHandler.write(vList, dataPath);
        }
        else{
            String data = video.getId()+","+video.getAuthor().getId()+","+
            video.getTitle()+","+video.getVideoPath()+",0,"+video.getDescription(); // New view count start from 0
            DataHandler.append(data, dataPath);
            if (video.getId() > maxId){
                maxId = video.getId();
            }
        }
    }

    public static int getMaxId(){
        if (videoList == null ){
            videoList = initVideoList();
        } 
        return maxId;
    }

    /**
     * @description this, is a function that will store data to memory from local file
     * private function
     */
    private static synchronized ArrayList<Video> initVideoList(){
        viewMap = new HashMap<>();
        ArrayList<Video> vdata = new ArrayList<>();
        ArrayList<String> videos = DataHandler.read(dataPath);
        String line;
        String cvsSplitBy = ",";
        Iterator<String> iterator = videos.iterator();
        while (iterator.hasNext()) {
            line = (String) (iterator.next());
            String[] ele = line.split(cvsSplitBy);
            String description = ele[5];
            for(int i=6; i < ele.length; i++){
                description = description+ ","+ ele[i]; 
            }
            User author =UserController.getUserById(Integer.parseInt(ele[1]));
            Video data = new Video(Integer.parseInt(ele[0]), author, ele[2],ele[3], description);
            //videoList.add(data);
            vdata.add(data);   
            maxId++;
            viewMap.put(data, (Integer) Integer.parseInt(ele[4]));
        }
        return vdata;
    }

    /**
     * This method add count of view, to both csv and the hashmap.
     * @param video to be add view count
     * @param increment the increment of view count.
     * @since 0.6
     */
    public static void addView(Video video, int increment){
        viewMap.put(video, viewMap.get(video) + increment);
        ArrayList<String> list = new ArrayList<>();
        for(Video s : videoList){
            String element = s.getId()+","+s.getAuthor().getId()+","+
            s.getTitle()+","+s.getVideoPath()+"," + viewMap.get(s) + 
            ","+s.getDescription();
            list.add(element);
        }
        DataHandler.write(list, dataPath);
    }

    /**
     * Check the view count of specific video
     * @param video to be checked
     * @return the count of view
     * @since 0.6
     */
    public static int getView(Video video){
        return viewMap.get(video);
    }

    public static void main(String[] args) {
        ArrayList<Video> test = VideoDB.getVideos();
        Video v = new Video(7, UserController.getUserById(3), "A", "BOY/a.mp4", "A sport, a new sport");
        VideoDB.addVideo(v);
        System.out.println(test.size());
        System.out.println(getView(test.get(0)));
        addView(test.get(0), 1);
        System.out.println(getView(test.get(0)));
    }
}
