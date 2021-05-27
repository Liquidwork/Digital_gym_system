/**
 * A static money controller which provide methods to access finance data
 */
public class CashController {

    private CashController(){ // Set the Controller invisible

    }

    /**
     * This is a method that will control the customers money,
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
    
    /**
     * Get cash balance of a customer.
     * @param customer to be checked
     * @return the cash balance
     */
    public static double getCash(Customer customer){
        double money = CashDB.getMoney(customer.getId());
        return money;
    }



}
