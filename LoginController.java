import exceptions.*;

/**
 * A login controller which provide methods to access user's login data 
 */
public class LoginController {

    /**
     * <p>Login to an account, initiate and return the customer if success, return null if login failed.
     * The method read a file of login data to check for the user's account</p>
     * <p>Will add 1 to user login count.</p>
     * @see LoginController#getLoginCount(User)
     * @param name username to login, name is ignore to case
     * @param password password of login account
     * @return the user if login success, otherwise exceptions will be thrown.
     * @throws PasswordException Password was wrong
     * @throws NoMemberException No such member found
     */
    public static synchronized User login(String name, String password) throws PasswordException, NoMemberException {
        User user = UserController.getUserByUsername(name);
        if (user == null) {
            throw new NoMemberException("You are not a member yet!");
        }
        if (!UserDB.checkPassword(user, password)){
            throw new PasswordException("Wrong password");
        }
        addLoginCount(user);
        return user;
    }

    /**
     * Register an account, initiate and return the user if success, return null if register failed.
     * The method read a file of login data to check if new account valid, then create a new one to
     * the end of the file with an auto-allocated id.
     * The new uid will be the (max(id) + 1).
     * @param name username to login
     * @param password password of login account
     * @param type {@link User.Type} type to be registered, (admin not advised)
     * @throws IllegalException Password or Username illegal, see implNote
     * @throws MemberExistedException Username already taken, which is ignore to cases
     * @implNote Password pattern "[A-Za-z0-9]{6,20}", Username pattern "[A-Za-z0-9]{4,20}"
     */
    public static synchronized void register(String name, String password, User.Type type) throws IllegalException, MemberExistedException {
        if (type == null){
            throw new NullPointerException("type is illegal");
        }
        String usrPattern="[A-Za-z0-9]{4,20}";
        if(!name.matches(usrPattern)){
            throw new IllegalException("The username is illegal");
        }
        String pswPattern="[A-Za-z0-9]{6,20}";// specifies the format of password
        if(!password.matches(pswPattern)){//The password is illegal and throw an exception.
            throw new IllegalException("The password is illegal");
        }
        if (UserController.getUserByUsername(name) != null) {
            throw new MemberExistedException("This username has been taken.");
        }
        UserDB.addUser(name, password, type);
    }

    /**
     * Add one count to login count
     * @param user to add count to
     * @since 0.6
     */
    public static void addLoginCount(User user){
        UserDB.addLoginCount(user, 1);
    }

    /**
     * get the login count of the user
     * @param user to be check
     * @return the login count
     * @since 0.6
     */
    public static int getLoginCount(User user){
        return  UserDB.getLoginCount(user);
    }


    /**
     * Some test methods.
     */
    public static void main(String[] args) throws Exception {
        System.out.println(login("winter", "14530529"));
        System.out.println(login("luca", "123456"));
    }

}
