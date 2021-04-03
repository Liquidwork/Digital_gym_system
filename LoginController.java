public class LoginController {
    private static final String loginPathname = "./data/login.csv"; // file path of login data

    /**
     * Login to an account, initiate and return the user if success, return null if login failed. 
     * The method read a file of logindata to check for the user's account 
     * @param name username to login
     * @param password password of login account
     * @return the user if login success, null if login failed
     */
    public User login(String name, String password){
        // TODO: Read a csv to check if user's login data correct, return user if login successfully, otherwise null will be returned
        return null;
    }

    /**
     * Register an account, initiate and return the user if success, return null if register failed. 
     * The method read a file of logindata to check if new account valid, then create a new one to 
     * the end of the file with an auto-allocated id.
     * @param name username to login
     * @param password password of login account
     * @return the user if login success, null if login failed
     */
    public User register(String name, String password){
        // TODO: Read a csv to check if user's register data valid, return user if register successfully, otherwise null will be returned
        return null;
    }
}
