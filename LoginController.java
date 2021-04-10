import java.io.*;
import java.util.ArrayList;

/**
 * A login controller which provide methods to access user's login data
 */
public class LoginController {
    private static final String loginPathname =  "./data/login.csv"; // file path of login data
    private static final String cashPathname = "./data/cash.csv"; // file path of cash
    private static final ArrayList<String> idlist=new ArrayList<>();
    private static final ArrayList<String> namelist= new ArrayList<String>();
    private  static  final ArrayList<String> passwordlist= new ArrayList<String>();
    private static final ArrayList<String> typelist= new ArrayList<>();

    /**
     * Login to an account, initiate and return the customer if success, return null if login failed.
     * The method read a file of login data to check for the user's account
     * @param name username to login
     * @param password password of login account
     * @return the user if login success, null if login failed
     * @seeUser
     */
    public static User login(String name, String password) throws MyException {
        // TODO: Read a csv to check if user's login data correct, return user if login successfully, otherwise null will be returned

        File csv=new File(loginPathname);
        File cash=new File(cashPathname);
        BufferedReader br=null;
        BufferedReader br1=null;
        try{
            br=new BufferedReader(new FileReader(csv));
            br1=new BufferedReader(new FileReader(cash));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line=null;
        int flag=0;
        int i=1;
        try {
            while ((line= br.readLine())!=null) {//read each line of File login
                String item[]=line.split(",");
                typelist.add(item[3]);
                if((name.equals(item[1]))&&(password.equals(item[2]))) { //compare the argument name and password with File csv and if the user is a member
                    //return diffirent types of users
                    if (typelist.get(i - 1).equals("customer")) {
                        Customer customer = new Customer(i, item[1]);// create a customer and return it
                        return customer;
                    } else if (typelist.get(i - 1).equals("admin")) {
                        Admin admin = new Admin(i, item[1]);
                        return admin;
                    } else {
                        Trainer trainer = new Trainer(i, item[1]);
                        return trainer;
                    }
                }
                i+=1;
            }
            if(flag==0){// the user is not a member  and throw a exception
                MyException myException=new MyException("You are not a member");
                throw myException;
            }
        }catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    /**
     * Register an account, initiate and return the user if success, return null if register failed.
     * The method read a file of logindata to check if new account valid, then create a new one to
     * the end of the file with an auto-allocated id.
     * @param name username to login
     * @param password password of login account
     * @return the user if login success, null if login failed
     * @seeUser
     */
    public static User register(String name, String password, String type) throws MyException {
        // TODO: Read a csv to check if user's register data valid, return user if register successfully, otherwise null will be returned
        File csv=new File(loginPathname);
        File cash=new File(cashPathname);
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
        try{
            while ((line= br.readLine())!=null){
                String item[]=line.split(",");
                idlist.add(item[0]);
                namelist.add(item[1]);
                passwordlist.add(item[2]);
                typelist.add(item[3]);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

        String pattern="([0-9](\\w*)){8,20}";// specifies the format of password

        while (true){// If the password is wrong, it will keep looping
            boolean flag0=password.matches(pattern);
            if(!flag0){//The password is illegal and throw an exception.
                MyException myException=new MyException("The password is illegal");
                throw myException;
//                return null;
            }
            else {
                break;
            }
        }
        boolean flag=true;
        int id=0;
        while(true){
            for(int i=0;i<namelist.size();i++){
                if((name.equals(namelist.get(i)))&&(password.equals(passwordlist.get(i)))){ // if the user is already a member

                    flag=false;
                    break;
                }
            }
            if (!flag){// user should login in, so return null
                //throw an exception
                MyException myException=new MyException("You are already a member");
                throw myException;
            }
            else {// if the user is a new customer
                namelist.add(name);
                passwordlist.add(password);
                typelist.add(type);
                try{
                    BufferedWriter writer= new BufferedWriter(new FileWriter(csv,true));//write the info to File login
                    BufferedWriter writer1= new BufferedWriter(new FileWriter(cash,true));// write the info to File cash
                    id= namelist.size();
                    String newer=id+","+name+","+password+","+type;
                    String newer1=id+","+"0.00";
                    writer.write('\n');
                    writer1.write('\n');
                    writer.write(newer);
                    writer1.write(newer1);
                    writer.close();
                    writer1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
                if (type.equals("customer")){
                    Customer customer=new Customer(id,name);
                    return customer;
                }
                else if(type.equals("trainer")){
                    Trainer trainer=new Trainer(id,name);
                    return trainer;
                }
                else {
                    MyException myException=new MyException("Type is wrong");
                    throw myException;
                }

            }
        }

    }

}