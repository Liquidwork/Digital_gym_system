import java.util.List;

public interface Comments {
    public void sendComment(String text, User author);
    public List<Comment> getComments();
    public void clearComments();

    public class Comment{
        
    }
}
