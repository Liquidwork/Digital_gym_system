import java.util.ArrayList;


public class UserDB{
    public static ArrayList<User> usersList;
    private static final String userPath = "./data/login.csv";
    
    
    public static ArrayList<User> getUsersList() {
        if (usersList == null) {
            initUsersList();
        }
        return usersList;
    }

    private static void initUsersList() {
        usersList = new ArrayList<>();
        ArrayList<String> list = DataHandler.read(userPath);
        for(String s : list){
            String[] pieces = s.split(",");
            switch(pieces[3].toLowerCase()){
                case "customer":
                    usersList.add(new Customer(Integer.parseInt(pieces[0]), pieces[1])); // Initialize a customer here
                break;
                case "admin":
                    usersList.add(new Admin(Integer.parseInt(pieces[0]), pieces[1])); // Initialize an admin here
                break;
                case "trainer":
                    usersList.add(new Trainer(Integer.parseInt(pieces[0]), pieces[1])); // Initialize a trainer here
                break;
                default:
                System.err.println("User type undefined"); // Continue the loop with a error printed on errIO
                continue;
            }
        }
    }
}