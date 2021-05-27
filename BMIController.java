import java.util.ArrayList;

public class BMIController {
    
    private static final String dataPath= "./data/BMI.csv";

    /**
     * @description this function get a BMI by user
     * and return {@code null} if no BMI of this user
     * @param {@link User}
     * @return {@link BMI}
     */
    public static BMI getBmiByUser(User user){
        for(BMI s : BMIDB.getBmis()){
            if(s.getUser().getId()==user.getId()){
                return s;
            }
        }
        return null;
    }

    /**
     * <p> Add a new BMI information or change the existing one to local with matched user 
     * @param {@link User} user
     * @param height
     * @param weight
     */
    public static void addBMI(User user, double height, double weight){
        BMIDB.addBMI(new BMI(user, height, weight));
    }

    /**
     * <p> Delete a BMI information of the user who want to delete it
     * @param {@link User} user
     */
    public static void deleBMI(User user){
        BMIDB.deleBMI(user);
    }

    public static void main(String[] args){
        User user = new Customer(2, "luca");
        BMI bmi = getBmiByUser(user);
        System.out.println(bmi);
        User user1 = new Customer(3, "Umihara");
        addBMI(user1, 140, 40);
        User user2 = new Customer(1, "werty");
        //deleBMI(user1);
    }
}
