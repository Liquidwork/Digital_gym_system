import java.util.ArrayList;

public class Customer extends User {
    private double money;
    //private String[] trainers;                    //future
    //private ArrayList<Integer> favouratevideos;   //future
    //private List<ExerciseRecord> exerciseRecordList;  //to do
    
    /**
     * This is the constructor of a customer extending user. Only extra param will be listed
     * Only has new setter and getter methods for money
     * @param money  stored information for the balance
     */
    public Customer(int id, String name, Type type, double money) {
        super(id, name, type);
        this.money = money;
    }

    public void setMoney(double money) {
        this.money =money;
    }

    public double getMoney() { return money;}

}
