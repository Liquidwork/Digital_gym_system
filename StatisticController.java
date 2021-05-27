import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * <p>This controller provides statistical data for administrator.</p>
 * <p>Use the constructor to get a static vault storing all data, and get them by
 * the getter methods.</p>
 * @author Winter Wang
 * @since 0.6
 */
public class StatisticController {
    
    private int[] userCount = new int[3]; // Customer, Trainer, Admin count respectively
    private int loginCount;
    private int chatCount;
    private int chatMessageCount;
    private int totalCourseCount;
    private int courseCount; // Upcoming courses
    private int videoCount;
    private int videoViewCount;
    private int videoCommentCount;

    /**
     * Construct a new {@code StatisticController} with full initialization to all the data.
     * Some <b>get methods</b> will be provided to get those result in specified format.
     */
    public StatisticController(){
        this.refreshUserCounts();
        this.refreshChatCounts();
        this.refreshCourseCount();
        this.refreshVideoCounts();
    }

    /**
     * Get count of all users even including <i>Admin</i>.
     * @return count of all user
     */
    public int getUserCount(){
        return this.userCount[0] + this.userCount[1] + this.userCount[2];
    }

    /**
     * Get count of <b>customers</b>.
     * @return count of customer
     */
    public int getCustomerCount(){
        return this.userCount[0];
    }

    /**
     * Get count of <b>trainers</b>.
     * @return count of trainer
     */
    public int getTrainerCount(){
        return this.userCount[1];
    }

    /**
     * Get count of <b>admins</b>.
     * @return count of admin
     */
    public int getAdminCount(){
        return this.userCount[2];
    }

    /**
     * Get count of user logins no matter what type of user it is.
     * @return count of total login
     */
    public int getLoginCount() {
        return this.loginCount;
    }


    /**
     * <p>Get count of <b>chats</b> between users.
     * <p>This method actually count chat files numbers.
     * @return count of chats
     */
    public int getChatCount() {
        return this.chatCount;
    }

    /**
     * Get count of chat messages, returning total message count of
     * all chats.
     * @return count of chat message
     */
    public int getChatMessageCount() {
        return this.chatMessageCount;
    }

    /**
     * Get count of courses, no matter ended or upcoming ones.
     * @return count of total course
     */
    public int getTotalCourseCount() {
        return this.totalCourseCount;
    }

    /**
     * Get count of upcoming courses.
     * @return count of upcoming course
     */
    public int getUpcomingCourseCount() {
        return this.courseCount;
    }

    /**
     * Get count of all videos.
     * @return count of video
     */
    public int getVideoCount() {
        return this.videoCount;
    }

    /**
     * Get count of all comment messages in all videos.
     * @return count of all video comment
     */
    public int getVideoCommentCount() {
        return this.videoCommentCount;
    }

        /**
     * Get count of views of all videos.
     * @return all views
     */
    public int getVideoViewCount() {
        return this.videoViewCount;
    }


    @Override
    public String toString() {
        return "{" +
            " userCount='" + getUserCount() + "'" +
            ", loginCount='" + getLoginCount() + "'" +
            ", chatCount='" + getChatCount() + "'" +
            ", chatMessageCount='" + getChatMessageCount() + "'" +
            ", totalCourseCount='" + getTotalCourseCount() + "'" +
            ", courseCount='" + getUpcomingCourseCount() + "'" +
            ", videoCount='" + getVideoCount() + "'" +
            ", videoCommentCount='" + getVideoCommentCount() + "'" +
            ", videoViewCount='" + getVideoViewCount() + "'" +
            "}";
    }

    /**
     * Refresh all types of user count
     */
    private void refreshUserCounts(){
        // Reset counters
        this.userCount[0] = 0;
        this.userCount[1] = 0;
        this.userCount[2] = 0;
        this.loginCount = 0;

        // Count one by one. 
        for (User u : UserController.getUsersList()) {
            if (u instanceof Customer){
                this.userCount[0]++;
            }else if (u instanceof Trainer){
                this.userCount[1]++;
            }else{
                this.userCount[2]++;
            }
            this.loginCount += LoginController.getLoginCount(u);
        }
    }

    /**
     * Refresh chat count and message count.
     */
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
                reader.skip(Long.MAX_VALUE); // Skip to end of the file
                this.chatMessageCount += reader.getLineNumber();
                reader.close();
            } catch(IOException e){
                e.printStackTrace();
            }
        }

    }

    /**
     * Refresh course count of all courses and upcoming courses
     */
    private void refreshCourseCount(){
        this.courseCount = 0;
        this.totalCourseCount = 0;
        File chatFolder = new File("./data/live/"); // Directory to be checked
        File[] filesList = chatFolder.listFiles((dir, name) -> name.contains(".csv")); // get a file list contains ".csv"
        SimpleDateFormat dateTran = new SimpleDateFormat("yyyy-MM-dd");
        for (File f : filesList){
            try{
                LineNumberReader reader = new LineNumberReader(new FileReader(f));
                reader.skip(Long.MAX_VALUE); // Skip to end of the file
                this.totalCourseCount += reader.getLineNumber();
                String name = (f.getName().split(".csv"))[0]; // Get name without .csv
                if (new Date().before(dateTran.parse(name))){ // Is it a upcoming course?
                    this.courseCount += reader.getLineNumber();
                }
                reader.close();
            } catch(IOException e){
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Refresh video count and video comments count.
     */
    private void refreshVideoCounts(){
        ArrayList<Video> list = VideoController.getVideosList();
        // Video count
        this.videoCount = list.size();
        // Video comment count
        this.videoCommentCount = 0; // Reset value.
        File chatFolder = new File("./data/comment/"); // Directory to be checked
        File[] filesList = chatFolder.listFiles((dir, name) -> name.contains(".csv")); // get a file list contains ".csv"
        // Count comments file by file
        for (File f : filesList){
            try{
                LineNumberReader reader = new LineNumberReader(new FileReader(f));
                reader.skip(Long.MAX_VALUE); // Skip to end of the file
                this.videoCommentCount += reader.getLineNumber();
                reader.close();
            } catch(IOException e){
                e.printStackTrace();
            }
        }
        // Count total views
        this.videoViewCount = 0; // Reset value.
        for (Video v : list){
            this.videoViewCount += VideoController.getView(v);
        }
    }

    public static void main(String[] args) {
        StatisticController statistic = new StatisticController();
        System.out.println(statistic);
    }

}
