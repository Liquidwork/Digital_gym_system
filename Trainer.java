public class Trainer extends User{
    /**
     * This is the basic constructor of a user. All subclasses should include it.
     *
     * @param id   the id of the user
     * @param name the name of the user
     * @param type the type of the user
     * @see User.Type
     * @param scorecount is number of times counted
     * @param score is average of totalscore
     */
    public Trainer(int id, String name, User.Type type, int scorecount, double score) {
        super(id, name, type);
    }
}
