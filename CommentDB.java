import java.util.ArrayList;
import java.util.Iterator;

public class CommentDB {
        //private String chatPath = "D:\\Work Zone\\GitHub\\Digital_gym_system\\data\\chat\\";
        private  String commentPath = "./data/comment/";
        private  ArrayList<Comment> comments = new ArrayList<>();
    
        /**
         * @description this is the contructor of a Comment obj 
         * @param video the video obj you want 
         */
        public CommentDB(Video video){
            commentPath = commentPath +video.getId()+".csv";
            initComments();
        }
    
        /**
         * @Description this is function for controller to get data 
         * @return list containing all comments 
         */
        public synchronized ArrayList<Comment> getComments(){
            return comments;
        }
    
        /**
         * @Description this is function for controller to record a new comments
         * @param message as Comment object store in local file
         */
        public synchronized void addComment(Comment message) {
            String sentence = message.getAuthor().getId()+","+message.getComment();
            DataHandler.append(sentence, commentPath);
            comments.add(message);
        }
    
        /**
         * @Description this is fnction to initialize a Comment database
         * it should only used when the contructor is called, so private
         */
        private synchronized void initComments() {
            ArrayList<String> data = DataHandler.read(commentPath);
            String line;
            String cvsSplitBy = ",";
            Iterator<String> iterator = data.iterator();
            while (iterator.hasNext()) {
                line = (String) (iterator.next());
                String[] ele = line.split(cvsSplitBy);
                String sentence = ele[1];
                for(int i=2; i < ele.length; i++){
                    sentence = sentence+ ","+ ele[i]; 
                }
                Comment tmp = new Comment(UserController.getUserById(Integer.parseInt(ele[0])),sentence);
                this.comments.add(tmp);
            }
        }

        /**
         * Remove all comments of this video
         */
        public synchronized void removeAll(){
            DataHandler.write(new ArrayList<String>(), this.commentPath);
        }

    public static void main(String[] args) {
        User auser = UserController.getUserById(3);
        User b =  UserController.getUserById(5);
        User c =  UserController.getUserById(8);
        Video alpha = new Video(1, auser, "first", "path","description");
        CommentDB test = new CommentDB(alpha);
        System.out.println("Test1");
        for(Comment e : test.getComments()){
            System.out.println(e.toString());
        }
        Comment line = new Comment(b, "Comments from B");
        test.addComment(line);
        System.out.println("Test2");
        for(Comment e : test.getComments()){
            System.out.println(e.toString());
        }
        Comment another = new Comment(c, "Comment from C");
        test.addComment(another);
        test =new CommentDB(alpha);
        System.out.println("Test3");
        for(Comment e : test.getComments()){
            System.out.println(e.toString());
        }
    }    
}
