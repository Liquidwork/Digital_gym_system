import java.util.ArrayList;

/**
 * UserController provide method to find a specific {@link User} according to 
 * its id, username and get a list or sublist of Users by type.
 */
public class UserController {
    /**
     * <p>Get the user by its id.
     * <p>Return {@code null} if {@link User} not found
     * @param id the id to be find
     * @return {@link User} found
     */
    public static User getUserById(int id){
        for (User u : UserDB.getUsersList()) {
            if (u.getId() == id)
                return u;
        }
        return null;
    }

    /**
     * <p>Get the user by its username.
     * <p>The method is not case sensitive, and a username should only include one 
     * <p>Return {@code null} if {@link User} not found
     * @param username the iserma,e tobe find
     * @return {@link User} found
     */
    public static User getUserByUsername(String username){
        for (User u : UserDB.getUsersList()) {
            if (u.getName().equalsIgnoreCase(username))
                return u;
        }
        return null;
    }

    /**
     * <p>Get a list with all the {@link User} in user list no matter what type it is.
     * <p>Return a empty list if no element fits the rule.
     * @return {@link ArrayList} of {@link User}
     */
    public static ArrayList<User> getUsersList(){
        return new ArrayList<>(UserDB.getUsersList());
    }

    /**
     * <p>Get a list with all the {@link Trainer} in user list.
     * <p>Return a empty list if no element fits the rule.
     * @return {@link ArrayList} of {@link Trainer}
     */
    public static ArrayList<Trainer> getTrainersList(){
        ArrayList<Trainer> list = new ArrayList<>();
        for (User u : UserDB.getUsersList()) {
            if (u instanceof Trainer){
                list.add((Trainer) u); // Convert from parent to child
            }
        }
        return list;
    }

    /**
     * <p<Get a list with all the {@link Customer} in user list.
     * <p>Return a empty list if no element fit the rule.
     * @return {@link ArrayList} of {@link Customer}
     */
    public static ArrayList<Customer> getCustomersList(){
        ArrayList<Customer> list = new ArrayList<>();
        for (User u : UserDB.getUsersList()) {
            if (u instanceof Customer){
                list.add((Customer) u); // Convert from parent to child
            }
        }
        return list;
    }
}
