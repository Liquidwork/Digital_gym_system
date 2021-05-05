import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import exceptions.NotActiveUserException;
/**
 * <p>{@code ChatController} between a customer and a trainer. Providing method to 
 * send and recieve messages.
 * <p>The class may be extended for more circumstances to use such as chatting between 2
 * users no matter its type or even a group chatting
 */
public class ChatController {

    private Customer customer;
    private Trainer trainer;
    private ChatDB dataList;


    /**
     * Initialize a {@link ChatController} between a customer and a trainer.
     * @param customer customer to chat with
     * @param trainer trainer to chat with
     */
    public ChatController(Customer customer, Trainer trainer) {
        this.customer = customer;
        this.trainer = trainer;
        this.dataList = new ChatDB(customer.getId(), trainer.getId());
    }

    /**
     * <p>Get a full chat history between these users.
     * <p>Return an empty list if no message was sent before
     * @return An {@link ArrayList} of chat
     */
    public ArrayList<Chat> getMessagesList(){
        return new ArrayList<Chat>(this.dataList.getChats());
    }


    /**
     * <p>Send a message in this chatting
     * @param user {@link User} to send the text
     * @param message The {@link String} to be send
     * @throws NotActiveUserException {@link RuntimeException} that indicating not an active user in this chat send a message
     */
    public void Send(User user, String message){
        int userId = user.getId();
        if (user instanceof Customer && userId == this.customer.getId()) {
            Chat send = new Chat(1,message);
            dataList.addChat(send);
        } else if (user instanceof Trainer && userId == this.trainer.getId()){
            Chat send = new Chat(0,message);
            dataList.addChat(send);
        } else{
            throw new NotActiveUserException();
        }

    }

    /**
     * <p>Look for chat partners of this User, who had chat with this user before.
     * <p>The more recent chatter will be shown in the front of the list.
     * <p>Return an empty list if no entity found.
     * @param user to be checked for
     * @return An {@link ArrayList} of {@link User} who chat with this user before
     */
    public static ArrayList<User> getChatPartners(User user){
        ArrayList<User> usersList = new ArrayList<>();
        HashMap<User, Long> userChatTime = new HashMap<>();
        int userId = user.getId();
        File chatFolder = new File("./data/chat/"); // Directory to be checked
        File[] filesList = chatFolder.listFiles((dir, name) -> name.contains(".csv")); // get a file list contains ".csv"
        for (File f : filesList) {
            String s = f.getName();
            int[] ids = new int[2];
            int[] index = {s.indexOf("-"), s.indexOf(".")};
            ids[0] = Integer.parseInt(s.substring(0, index[0])); // Find int before '-'
            ids[1] = Integer.parseInt(s.substring(index[0] + 1, index[1])); // Find int between '-' and '.'
            if (ids[0] == userId) {
                usersList.add(UserController.getUserById(ids[1]));
                userChatTime.put(UserController.getUserById(ids[1]), f.lastModified()); // Record last modified with chat partner
            } else if (ids[1] == userId) {
                usersList.add(UserController.getUserById(ids[0]));
                userChatTime.put(UserController.getUserById(ids[0]), f.lastModified()); // Record last modified with chat partner
            }
        }
        Collections.sort(usersList, (userA, userB) -> { // lambda expression to regulate a sorting rule.
            if (userChatTime.get(userA) <= userChatTime.get(userB)){
                return 1;
            } else {
                return -1;
            }
        }); // Sort userlist with last modified time, in descending order.
        return usersList;
    }

    /**
     * test function
     * @param arg
     */
    public static void main(String arg[]){
        
        System.out.println(getChatPartners(UserController.getUserById(2)));
    }
}
