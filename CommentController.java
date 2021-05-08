import java.util.ArrayList;

public class CommentController {

    private Video currentVideo;
    private CommentDB dataList;
    private Comment comment;

    /**
     * Initiate a comment controller that interactive with this {@link Video}.
     * @param video to operate
     */
    public CommentController(Video video){
        this.currentVideo = video;
        this.dataList = new CommentDB(currentVideo);
    }

    /**
     * <p>Get comments under a video.
     * <p>Return an empty list if there is no comment in this video.
     * @return an {@code ArrayList} of comments
     */
    public ArrayList<Comment> getComments(){
        return new ArrayList<Comment>(this.dataList.getComments());
    }

    /**
     * <p>Send a comment from a user account.
     * <p>A user may send many comments and will not interfere.
     */
    public void sendComments(User author, String Comment){
        this.comment = new Comment(author,Comment);
        dataList.addComment(comment);
    }

    public static void main(String arg[]){
        User a = UserController.getUserById(1);
        User bot = UserController.getUserById(2);
        Video video = new Video(1,a,"1","path","bot");
        CommentController commentController = new CommentController(video);
        System.out.println(commentController.getComments());
        System.out.println("-----------------------------");
        commentController.sendComments(bot,"bot");
        System.out.println(commentController.getComments());
    }
}
