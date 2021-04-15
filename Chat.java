/**
 * This is a chat class that contain  type and message arttribute
 * No src or dst because controller should handle it
 * 
 */

public class Chat {

    private int type;
    private String message;

    /**
     * @Description This is the conructor of chat
     * @param type  define if the message is from customer to trainer(True) or  conversely(False)
     * @param message  The string of one chat
     */
    public Chat(int type, String message) {
        this.type = type;
        this.message = message;
    }

    public int getType() {
        return this.type;     
    }
    
    public String getMsg() {
        return this.message;
    }
}
