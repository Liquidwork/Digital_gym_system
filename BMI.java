

/**
  {@code BMI}class provides the information about a BMI with ues's id, height and weight
 */
public class BMI {
    private User user;
    private double height;
    private double weight;

    /**
     * Construct a new {@code BMI} class with all fields in it
     * @param user
     * @param height
     * @param weight
     */
    public BMI(User user, double height, double weight){
        this.user=user;
        this.height=height;
        this.weight=weight;
    }

    /**
     * Get the user of BMI
     * @return {@link User}  type user
     */
    public User getUser(){
        return this.user;
    }

    /**
     * Get the height of the BMI
     * @return height of the BMI
     */
    public double getHeight(){
        return this.height;
    }

    /**
     * Get the weight of the BMI
     * @return weight of the BMI
     */
    public double getWeight(){
        return this.weight;
    }

    /**
     * Get the index of the BMI
     * @return index of the BMI
     */
    public double getBMI(){
        return (this.weight / ((this.height / 100) * (this.height / 100)));
    }

    /**
     * Get a String with all field information 
     */
    @Override
    public String toString(){
        return "{" +
        " user='" + getUser() + "'" +
        ", height='" + getHeight() + "'" +
        ", weight='" + getWeight() + "'" +
        ", BMI='" + getBMI() + "'" +
        "}";
    }

}
