import java.util.List;
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
    private List<Chat> history; // You can impletement it by any class or usage

    /**
     * Initialize a {@link ChatController} between a customer and a trainer.
     * @param customer customer to chat with
     * @param trainer trainer to chat with
     */
    public ChatController(Customer customer, Trainer trainer) {
        this.customer = customer;
        this.trainer = trainer;
        // TODO: Initialize the chatting history map here
    }

    /**
     * <p>Get a full chat history between these users.
     * <p>Return an empty list if no message was sent before
     * @return
     */
    public List<Chat> getMessagesList(){
        return null;
    }

    /**
     * <p>Send a message in this chatting
     * @param user {@link User} to send the text
     * @param message The {@link String} to be send
     * @throws NotActiveUserException {@link RuntimeException} that indicating not an active user in this chat send a message 
     */
    public void Send(User user, String message){
        // TODO: implement
    }

    /**
     * Clear all messages in this chat, including the message file.
     */
    public void clearMessages(){
        // TODO: IMPLEMENT
    }

}
