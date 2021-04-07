/**
 * A  static money controller which provide methods to access finance data
 */
public class CashController {
    /**
     * @Descrption: This is a function that will control the customers money,
     * negative means spending, positive means adding money.
     * It will return judging by the result(success or failure) of action
     * You can' spend more thant you have now
     * @param customer the customer object
     * @param amount the amount of money changed,
     * @return  boolean  false means you can' change
     */
    public static void addCash(Customer customer, double amount){
        double updated = CashDB.getMoney(customer.getId()) + amount;
        if (updated<0){
            System.out.println("You don't have enough balance");
        }
        else {
            CashDB.setMoney(customer.getId(), updated);
            System.out.println("Successful action");
        }
    }

    //Test main function
    public static void main(String[] args) {
        int id =4;
        CashDB cashData = new CashDB();
        Customer customer = new Customer(id,"Luca", User.Type.Customer, cashData.getMoney(id));
        System.out.println("Money from object: "+CashDB.getMoney(customer.getId()));
        CashController.addCash(customer,1.0);
        System.out.println("Updated money from object: "+CashDB.getMoney(customer.getId()));
    }

}
