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


    private String name;
    private int id;

    /**
     * This is the basic constructor of a user. All subclasses should include it.
     * @param id the id of the user
     * @param name the name of the user
     */
    public User(int id, String name){
        this.id = id;
        this.name = name;
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
     * Get a String with all field information,
     */
    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", id='" + getId() + "'" +
            "}";
    }


}
