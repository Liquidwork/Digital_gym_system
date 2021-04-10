import java.io.*;
import java.util.ArrayList;
import exceptions.*;

/**
 * A login controller which provide methods to access user's login data 
 */
public class LoginController {
    private static final String loginPathname =  "./data/login.csv"; // file path of login data
    private static final String cashPathname = "./data/cash.csv"; // file path of cash
    private static final ArrayList<String> idlist=new ArrayList<>();
    private static final ArrayList<String> namelist= new ArrayList<String>();
    private  static  final ArrayList<String> passwordlist= new ArrayList<String>();
    private static final ArrayList<User.Type> typelist= new ArrayList<>();

    /**
     * Login to an account, initiate and return the customer if success, return null if login failed.
     * The method read a file of login data to check for the user's account
     * @param name username to login, name is ignore to case
     * @param password password of login account
     * @return the user if login success, null if login failed
     * @throws PasswordException Password was wrong
     * @throws NoMemberException No such member found
     * @throws IOException IOException in loading files
     * @seeUser
     */
    public static User login(String name, String password) throws PasswordException, NoMemberException, IOException {
        File csv=new File(loginPathname);
        BufferedReader br=null;
        try{
            br=new BufferedReader(new FileReader(csv));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line=null;
        int flag=0;
        int i=1;
        while ((line= br.readLine())!=null) {//read each line of File login
            String item[]=line.split(",");
            item[3]=item[3].substring(0,1).toUpperCase()+item[3].substring(1);
            User.Type type= User.Type.valueOf(item[3]);
            typelist.add(type);
            if((name.equalsIgnoreCase(item[1]))) { //compare the argument name and password with File csv and if the user is a member
                if (password.equals(item[2])){
                    //return diffirent types of users
                    if (typelist.get(i - 1).equals(User.Type.Customer)) {
                        Customer customer = new Customer(i, item[1]);// create a customer and return it
                        return customer;
                    } else if (typelist.get(i - 1).equals(User.Type.Admin)) {
                        Admin admin = new Admin(i, item[1]);
                        return admin;
                    } else {
                        Trainer trainer = new Trainer(i, item[1]);
                        return trainer;
                    }
                }else {//password is wrong
                    PasswordException passwordException=new PasswordException("Password is wrong");
                    throw passwordException;
                }

            }
            i+=1;
        }
        if(flag==0){// the user is not a member  and throw a exception
            NoMemberException nomemberException=new NoMemberException("You are not a member");
            throw nomemberException;
        }
        return null;
    }

    /**
     * Register an account, initiate and return the user if success, return null if register failed.
     * The method read a file of logindata to check if new account valid, then create a new one to
     * the end of the file with an auto-allocated id.
     * The new uid will be the (max(id) + 1)
     * @param name username to login
     * @param password password of login account
     * @param type type to be registered, (admin not advised)
     * @return the user if login success, null if login failed
     * @throws IllegalException Password or Username illegal, see implNote
     * @throws MemberExistedException Username already taken, which is ignore to cases
     * @throws IOException IOExceptions in handling files
     * @see User.Type
     * @implNote Password pattern "[A-Za-z0-9]{6,20}", Username pattern "[A-Za-z0-9]{4,20}"
     */
    public static User register(String name, String password, User.Type type) throws IllegalException, MemberExistedException, IOException {
        if (type == null){
            throw new NullPointerException("type is illegal");
        }
        File csv=new File(loginPathname);
        BufferedReader br=null;
        try{
            br=new BufferedReader(new FileReader(csv)); // read each line of File login
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line=null;
        idlist.clear();// clear the arraylist
        namelist.clear();
        passwordlist.clear();
        typelist.clear();
        while ((line= br.readLine())!=null){
            String item[]=line.split(",");
            idlist.add(item[0]);
            namelist.add(item[1]);
            passwordlist.add(item[2]);
            item[3]=item[3].substring(0,1).toUpperCase()+item[3].substring(1);
            User.Type tpye=User.Type.valueOf(item[3]);
            typelist.add(tpye);
        }
        String usrPattern="[A-Za-z0-9]{4,20}";
        if(!name.matches(usrPattern)){
            throw new IllegalException("The username is illegal");
        }
        String pswPattern="[A-Za-z0-9]{6,20}";// specifies the format of password
        if(!password.matches(pswPattern)){//The password is illegal and throw an exception.
            throw new IllegalException("The password is illegal");
        }
        int id=1; //id start from 1
        int lineId, maxId = 0;
        for(int i=0;i<namelist.size();i++){
            if((name.equalsIgnoreCase(namelist.get(i)))){ // if the user is already a member
                MemberExistedException memberException=new MemberExistedException("Username already taken");
                throw memberException;
            }
            lineId = Integer.parseInt(idlist.get(i));
            if (maxId < lineId){ // Find the max id
                maxId = lineId;
            }
        }
        id = maxId + 1; // id is 1 if no record in login.csv
        namelist.add(name);
        passwordlist.add(password);
        typelist.add(type);
        BufferedWriter writer= new BufferedWriter(new FileWriter(csv,true));//write the info to File login
        String newer=id+","+name+","+password+","+type;
        writer.write(newer);
        writer.write('\n'); // Newline after recorded
        writer.close();
        switch (type){
            case Customer:
                return new Customer(id, name);
            case Trainer:
                return new Trainer(id, name);
            case Admin:
                return new Admin(id, name);
            default:
                return null;
        }
    }

    /**
     * Some test methods.
     */
    public static void main(String[] args) throws Exception {
        //System.out.println(login("winter", "14530529"));
        System.out.println(register("winterrain2", "a12345", User.Type.Trainer));
        System.out.println(login("winterrain2", "a12345"));
    }

}