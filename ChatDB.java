
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This is a database that will hold the data from one user to anothor
 * General rules:   file in chat folder name:   srcID-dstID.csv
 * srcID is always the Customer, dstID is always the Trainer
 * one-line data style  "0,the message", or "1,the meesage"
 * 1, means src send to dst,  0 means dst send to src. 
 */
public class ChatDB {
    
    //private String chatPath = "D:\\Work Zone\\GitHub\\Digital_gym_system\\data\\chat\\";
    private  String chatPath = "./data/chat/";
    private  ArrayList<Chat> chats = new ArrayList<>();

    /**
     * @description this is the contructor of a chat obj 
     * @param srcID provide the customer ID
     * @param dstID provide the tainer  ID
     */
    public ChatDB(int srcID, int dstID){
        chatPath = chatPath +srcID+"-"+dstID+".csv";
        initChats();
    }

    /**
     * @Description this is function for controller to get data
     * no data for src and dst beacuse controller already has it 
     * @return list contain  meesage and type, 
     */
    public synchronized ArrayList<Chat> getChats(){
        return chats;
    }

    /**
     * @Description this is function for controller to record a new chat
     * @param message as Chat object store in local file
     */
    public synchronized void addChat(Chat message) {
        String sentence = message.getType()+","+message.getMsg();
        DataHandler.append(sentence, chatPath);
        chats.add(message);
    }

    /**
     * @Description this is fnction to initialize a chat database
     * it should only used when the contructor is called, so private
     */
    private synchronized void initChats() {
        ArrayList<String> data = DataHandler.read(chatPath);
        String line;
        String cvsSplitBy = ",";
        Iterator<String> iterator = data.iterator();
        while (iterator.hasNext()) {
            line = (String) (iterator.next());
            String[] ele = line.split(cvsSplitBy);
            this.chats.add(new Chat(Integer.parseInt(ele[0]), ele[1]));
        }
    }

     public static void main(String[] args) {
        ChatDB test = new ChatDB(1, 3);
        System.out.println(test.getChats());
        Chat message = new Chat(1, "This is a test meesage, for test");
        test.addChat(message);
        System.out.println(test.getChats());
        test = new ChatDB(1, 3);
        System.out.println(test.getChats());
    }
}
