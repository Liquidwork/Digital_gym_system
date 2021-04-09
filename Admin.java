public class Admin extends User{
    /**
     * This is the basic constructor of a user. All subclasses should include it.
     *
     * @param id   the id of the user
     * @param name the name of the user
     * @param type the type of the user
     * @see User.Type
     */
    public Admin(int id, String name) {
        super(id, name);
    }
}
