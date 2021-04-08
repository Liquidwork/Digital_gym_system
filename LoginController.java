import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A login controller which provide methods to access user's login data 
 */
public class LoginController {
    private static final String loginPathname = "./data/login.csv"; // file path of login data
    private static final String cashPathname = "./data/cash.csv"; // file path of cash
    private static final ArrayList<String> idlist=new ArrayList<>();
    private static final ArrayList<String> namelist= new ArrayList<String>();
    private  static  final ArrayList<String> passwordlist= new ArrayList<String>();

    /**
     * Login to an account, initiate and return the customer if success, return null if login failed.
     * The method read a file of login data to check for the user's account
     * @param name username to login
     * @param password password of login account
     * @return the user if login success, null if login failed
     * @seeUser
     */
    public static User login(String name, String password){
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
        String line1=null;
        int flag=0;
        int i=1;
       try {
           while ((line= br.readLine())!=null) {//read each line of File login
                System.out.println(line);
                String item[]=line.split(",");
               idlist.add(item[0]);
               namelist.add(item[1]);
               passwordlist.add(item[2]);
               if((name.equals(item[1]))&&(password.equals(item[2]))){ //compare the argument name and password with File csv and if the user is a member
                   flag=1;
                   System.out.println("right");
                   User.Type type=User.Type.valueOf(item[3]);
                   double money=0.00;
                   while ((line1= br1.readLine())!=null){ //read each line of File cash
                       String mon[]=line1.split(",");
                       if (String.valueOf(i).equals(mon[0])){
                           money=Double.parseDouble(mon[1]);
                       }
                   }
                   Customer customer=new Customer(i,item[1],type,money);// create a customer and return it
                   return customer;
               }
               i+=1;
           }
           if(flag==0){// the user is not a member
               System.out.println("Sorry! You're not a member yet. \n"+"Please register!");
           }
           }catch (IOException e) {
           e.printStackTrace();
       }
      // System.out.println(namelist);

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
    public static Customer register(String name, String password) {
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
        try{
            while ((line= br.readLine())!=null){
                String item[]=line.split(",");
                idlist.add(item[0]);
                namelist.add(item[1]);
                passwordlist.add(item[2]);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("here"+idlist.size());
        Scanner scanner=new Scanner(System.in);
        System.out.println("There two types of members: Customer  Trainer. What type do you want to register:\n");// obtain the type the from the keyboard
        String str=scanner.nextLine();
        User.Type type=User.Type.valueOf(str);
        String pattern="([0-9](\\w*)){8,20}";// specifies the format of password

        while (true){// If the password is wrong, it will keep looping
            boolean flag0=password.matches(pattern);
            if(!flag0){
                System.out.println("The password is illegal. The" +
                        " right format: ([0-9](\\w*)){8,20}");
                Scanner scanner1=new Scanner(System.in);
                password=scanner1.nextLine();
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
                    System.out.println("You are already a member! Please login in");
                    flag=false;
                    break;
                }
            }
            if (!flag){// user should login in, so return null
                return null;
            }
            else {// if the user is a new customer
                namelist.add(name);
                passwordlist.add(password);
                try{
                    BufferedWriter writer= new BufferedWriter(new FileWriter(csv,true));//write the info to File login
                    BufferedWriter writer1= new BufferedWriter(new FileWriter(cash,true));// write the info to File cash
                    id= namelist.size();
                    String newer=id+","+name+","+password+","+str;
                    String newer1=id+","+"0.00";
                    writer.write('\n');
                    writer1.write('\n');
                    writer.write(newer);
                    writer1.write(newer1);
                    writer.close();
                    writer1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Register failed!");
                    return null;
                }
                System.out.println("Register successfully");
                Customer customer=new Customer(id,name,type,0.00);
                return customer;
            }
        }

    }

}
