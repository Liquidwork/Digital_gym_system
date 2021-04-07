public class CashController {
    /**
     * @Descrption: This is a function that will control the customers money,
     * in both the customer object and the database. You should create
     * a Customer and a CashDB before use it.  amount is negative means spending
     * positive means addingmoney. It will return judging by the result(success or failure) of action
     * @Param:  [Customer, amount, data]
     * @retun:  boolean
     */
    public static void addCash(Customer customer, double amount, CashDB data){
        double updated = customer.getMoney() + amount;
        System.out.println(customer.getMoney());
        System.out.println(amount);
        System.out.println(updated);
        if (updated<0){
            System.out.println("You don't have enough balance");
        }
        else {
            customer.setMoney(updated);
            data.setMoney(customer.getId(), updated);
            System.out.println("Successful action");
        }
    }
    //Test main function
    public static void main(String[] args) {
        int id =4;
        CashDB cashData = new CashDB();
        Customer customer = new Customer(id,"Luca", User.Type.Customer, cashData.getMoney(id));
        System.out.println("Money from object: "+customer.getMoney());
        CashController.addCash(customer,1.0,cashData);
        System.out.println("Updated money from object: "+customer.getMoney());

    }

}
