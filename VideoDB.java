import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class VideoDB {
   //private static final String basicPath = "D:\\Work Zone\\GitHub\\Digital_gym_system\\data\\video.csv";
   private static final String dataPath = "./data/video.csv";
   private static ArrayList<Video> videoList;

   public static void addVideo(Video video) {
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
    private static void initVideoList(){
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
            Video data = new Video(ele[0],, ele[2],ele[3], description)
            videoList.add(data);   
        }
    }
    public VideoDB() {
    }
}
