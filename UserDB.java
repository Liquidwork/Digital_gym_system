import java.util.ArrayList;
import java.util.HashMap;


public class UserDB{
    private static ArrayList<User> usersList;
    private static HashMap<User, String> passwordMap;
    private static int maxId = 0;
    private static final String userPath = "./data/login.csv";
    
    
    public static ArrayList<User> getUsersList() {
        if (usersList == null) {
            initUsersList();
        }
        return usersList;
    }

    /**
     * Check user for this specified {@link User}.
     * @param user user to be checked
     * @param password password that user entered
     * @return true if password matches with data stored
     */
    public static boolean checkPassword(User user, String password){
        if (passwordMap.get(user).equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * <p>Add a new user by indicating its attributes, into both list and file.
     * <p>This method will not check whether name is unique! Be sure checked before using this method.
     * @param name
     * @param password
     * @param type
     */
    public static void addUser(String name, String password, User.Type type){
        int id = ++maxId;
        User user;
        switch (type){
            case Customer:
                user = new Customer(id, name);
                break;
            case Trainer:
                user = new Trainer(id, name);
                break;
            case Admin:
                user = new Admin(id, name);
                break;
            default:
                throw new RuntimeException("Not valid user type");
        }
        usersList.add(user);
        passwordMap.put(user, password);
        DataHandler.append(id + "," + name + "," + password + "," + type, userPath);
    }

    private static void initUsersList(){
        usersList = new ArrayList<>();
        ArrayList<String> list = DataHandler.read(userPath);
        passwordMap = new HashMap<>();
        for(String s : list){
            String[] pieces = s.split(",");
            User user;
            int id = Integer.parseInt(pieces[0]);
            switch(pieces[3].toLowerCase()){
                case "customer":
                    user = new Customer(id, pieces[1]); // Initialize a customer here
                    break;
                case "admin":
                    user = new Admin(id, pieces[1]); // Initialize an admin here
                    break;
                case "trainer":
                    user = new Trainer(id, pieces[1]); // Initialize a trainer here
                    break;
                default:
                    System.err.println("User type undefined"); // Continue the loop with a error printed on errIO
                    continue;
            }
            usersList.add(user);
            passwordMap.put(user, pieces[2]);
            if (maxId < id){
                maxId = id;
            }
        }
    }
}