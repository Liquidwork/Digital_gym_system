/**
 * This is a database that will hold the data from one user to anothor
 * General rules:   file in chat folder name:   srcID-dstID.csv
 * srcID is always the customer, dstID is always the trainer
 * one-line data style  "0,the message", or "1,the meesage"
 * 1, means src send to dst,  0 means dst send to src. 
 */

import java.util.ArrayList;

public class ChatDB {
    
    //private static final String basicPath = "D:\\Work Zone\\GitHub\\Digital_gym_system\\data\\chat\\";
    private static final String basicPath = "./data/chat/";
    private static ArrayList<String> currentChats;
    private static int currentSrc;
    private static int currentDst;

    /**
     * @Description This function will only add dat to local file, because for chat record there's no
     * duplicate problems.
     * @param srcID the srcID of the customer
     * @param DstID the dstID of the trainer
     * @param sentence  the message you want to send followw the general rules 
     */
    public static void addChat(int srcID, int DstID, String sentence) {

        String path = basicPath + srcID + "-" + DstID+".csv";
        DataHandler.append(sentence, path);
        if (currentSrc == srcID &&currentDst == DstID){
            currentChats.add(sentence);
        }
    }

    /**
     * @Description This is the function that will return current chat data to controller,
     * Because there's only one object in class, if start new chat with other person, it will reload loal data  
     * @param srcID  the ID of customer
     * @param dstID  the ID of trainer
     * @return    all meesages as array list.
     */
    public static ArrayList<String> getChat(int srcID, int dstID) {
        String path = basicPath + srcID + "-" + dstID +".csv";
        if(srcID != currentSrc || dstID != currentDst){
            currentSrc = srcID;
            currentDst = dstID;
            currentChats = DataHandler.read(path);
        }
        return currentChats;
    }


     public static void main(String[] args) {
        ArrayList<String> a = getChat(1, 3);
        System.out.println(a);  
        String test = "1,Test functions, also test extra ";
        addChat(1,3, test);
        a =getChat(1, 3);
        System.out.println(a);
    }
}
