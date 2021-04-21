import java.util.ArrayList;
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
        int cusId,traId;
        cusId = customer.getId();
        traId = trainer.getId();
        this.dataList = new ChatDB(cusId,traId);
    }

    /**
     * <p>Get a full chat history between these users.
     * <p>Return an empty list if no message was sent before
     * @return
     */
    public ArrayList<Chat> getMessagesList(){
        return this.dataList.getChats();
    }


    /**
     * <p>Send a message in this chatting
     * @param user {@link User} to send the text
     * @param message The {@link String} to be send
     * @throws NotActiveUserException {@link RuntimeException} that indicating not an active user in this chat send a message
     */
    public void Send(User user, String message){
        if (user instanceof Customer) {
            Chat send = new Chat(1,message);
            dataList.addChat(send);
        } else if (user instanceof Trainer){
            Chat send = new Chat(0,message);
            dataList.addChat(send);
        } else{
            throw new NotActiveUserException();
        }

    }

    /**
     * test function
     * @param arg
     */
    public static void main(String arg[]){
        Customer customer = new Customer(1,"bot");
        Trainer trainer = new Trainer(3,"a");
        Admin admin = new Admin(2,"b");
        ChatController c = new ChatController(customer,trainer);
        String message = "bot";
        c.Send(admin,message);
        System.out.println(c.getMessagesList());
    }
}
