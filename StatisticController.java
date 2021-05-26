import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

/**
 * This controller provide statistical data for administrator.
 * @author Winter Wang
 * @since 0.6
 */
public class StatisticController {
    
    private int[] userCount = new int[3]; // Customer, Trainer, Admin count respectively
    private int chatCount;
    private int chatMessageCount;
    private int courseCount;
    private int videoCount;
    private int videoCommentCount;

    /**
     * Construct a new {@code StatisticController} with full initialization to all the data.
     * Some <b>get methods</b> will be provided to get those result in specified format.
     */
    public StatisticController(){
        this.refreshUserCounts();
        this.refreshChatCounts();
    }

    private void refreshUserCounts(){
        // Reset counters
        this.userCount[0] = 0;
        this.userCount[1] = 0;
        this.userCount[2] = 0;

        // Count one by one. 
        for (User u : UserController.getUsersList()) {
            if (u instanceof Customer){
                this.userCount[0]++;
            }else if (u instanceof Trainer){
                this.userCount[1]++;
            }else{
                this.userCount[2]++;
            }
        }
    }

    private void refreshChatCounts(){
        // Get all chat files
        File chatFolder = new File("./data/chat/"); // Directory to be checked
        File[] filesList = chatFolder.listFiles((dir, name) -> name.contains(".csv")); // get a file list contains ".csv"
        this.chatCount = filesList.length;
        // Count messages
        this.chatMessageCount = 0; // Reset this value
        for (File f : filesList){
            try{
                LineNumberReader reader = new LineNumberReader(new FileReader(f));
                reader.skip(Long.MAX_VALUE);
                this.chatMessageCount += reader.getLineNumber();
                reader.close();
            } catch(IOException e){
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        StatisticController statistic = new StatisticController();
        System.out.println(statistic.chatCount);
        System.out.println(statistic.chatMessageCount);
    }

}
