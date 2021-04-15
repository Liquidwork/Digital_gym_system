import java.util.ArrayList;
import java.util.Iterator;

/**
 * A static class that will handle the data of money in local file
 * Also allow access from other classes
 */
public class CashDB {

    //private static final String cashPath = "D:\\Work Zone\\JavaZone\\SoftwareEng\\src\\data\\cash.csv";
    private static final String cashPath = "./data/cash.csv";
    private static ArrayList<String> cashData;


    /**
     * @descrption: This is a function that will change the data in local files
     * @param id   id of the customer
     * @param money  the actual value after updating
     * @return: none
     */
    public static void setMoney(int id, double money) {
        if (cashData == null){
            cashData = DataHandler.read(cashPath);
        }
        boolean existed = cashData.removeIf(e ->e.matches(id+",(.*)"));
        String updated = id + "," + money;
        cashData.add(updated);
        if (existed){
            DataHandler.write(cashData,cashPath);
        }
        else {
            DataHandler.append(updated,cashPath);
        }

    }


    /**
     * @Descrption: This is the function that will get the money amount of the user by id
     * @param id  id of the user
     * @return money the value in double. if not exist ,it will return 0
     */
    public static double getMoney(int id) {
        if (cashData == null){
            cashData = DataHandler.read(cashPath);
        }
        String line;
        String cvsSplitBy = ",";
        double cash = 0;
        Iterator<String> iterator = cashData.iterator();
        while (iterator.hasNext()) {
            line = (String) (iterator.next());
            String[] ele = line.split(cvsSplitBy);
            if (Integer.parseInt(ele[0])== id)
                cash = Double.parseDouble(ele[1]);
        }
        return cash;
    }

    //Test main function
    public static void main(String[] args) {
        System.out.println(CashDB.getMoney(4));
        CashDB.setMoney(4,37);
        System.out.println(CashDB.getMoney(4));

    }
}
