import java.util.ArrayList;

public class CommentController {

    private Video currentVideo;

    /**
     * Initiate a comment controller that interactive with this {@link Video}.
     * @param video to operate
     */
    public CommentController(Video video){
        this.currentVideo = video;
    }

    /**
     * <p>Get comments under a video.
     * <p>Return an empty list if there is no comment in this video.
     * @param video
     * @return an {@code ArrayList} of comments
     */
    public ArrayList<Comment> getComments(){
        // TODO: impletement this method.
        return null;
    }

    /**
     * <p>Send a comment from a user account.
     * <p>A user may send many comments and will not interfere.
     */
    public void sendComments(User author, String Comment){
        // TODO: impletement this method.
    }
}
