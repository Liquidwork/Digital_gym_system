import java.util.ArrayList;

public class VideoDB {
   //private static final String basicPath = "D:\\Work Zone\\GitHub\\Digital_gym_system\\data\\video.csv";
   private static final String Path = "./data/video.csv";
   private static final String desPath = "./data/video2.csv";
   private static ArrayList<String> videosList;
   private static ArrayList<String> descriptions;

   public static void addVideo(int trainerID, String title, String link) {
        if (videosList == null){
            videosList = DataHandler.read(Path);
            descriptions = DataHandler.read(desPath);
        }

    }
}
