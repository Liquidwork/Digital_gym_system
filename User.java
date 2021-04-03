/**
 * Abstract class User, providing basic operations and attributes to all users including 
 * Customers, Trainers and Admins.
 * @see User.Type
 */
public abstract class User {
    /**
     * This is a enum showing which type the user is.
     */
    public enum Type{Customer, Trainer, Admin};

    private Type type;
    private String name;
    private int id;

    /**
     * This is the basic constructor of a user. All subclasses should include it.
     * @param id the id of the user
     * @param name the name of the user
     * @param type the type of the user
     * @see User.Type
     */
    public User(int id, String name, Type type){
        this.id = id;
        this.name = name;
        this.type = type;
    }

    /**
     * Getter of user's name
     * @return attribute name
     */
    public String getName(){
        return this.name;
    }

    /**
     * Getter of user's id
     * @return the user's id
     */
    public int getId(){
        return this.id;
    }

    /**
     * Getter of type of user
     * @return enum type
     * @see User.Type
     */
    public Type getType(){
        return this.type;
    }
}
