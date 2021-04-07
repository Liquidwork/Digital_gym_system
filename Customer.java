import java.util.ArrayList;

public class Customer extends User {
    /**
     * This is the constructor of a customer. Only extra param will be listed
     * Only has new setter and getter for money
     * @param money  store for the balance
     * @param trainers future used for teachers
     * @param favouratevideos future used for favors
     */
    private double money;
    //private String[] trainers;                    //future
    //private ArrayList<Integer> favouratevideos;   //future
    //private List<ExerciseRecord> exerciseRecordList;  //to do

    public Customer(int id, String name, Type type, double money) {
        super(id, name, type);
        this.money = money;
    }

    public void setMoney(double money) {
        this.money =money;
    }

    public double getMoney() { return money;}

}
