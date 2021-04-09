/**
 * A static class that will handle the data of money in local file
 * Also allow access from other classes
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;


public class CashDB {

    //private static final String cashPath = "D:\\Work Zone\\JavaZone\\SoftwareEng\\src\\data\\cash.csv";
    private static final String cashPath = "./data/cash.csv";
    private static ArrayList<String> cash_data;

    private static ArrayList<String> readGeneral(String path){
        String line ;
        ArrayList<String> data =new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((line = br.readLine()) != null) {
                data.add(line);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private static boolean writeGeneral(ArrayList<String> list, String path){
        String line;
        boolean result = false;
        try{
            File csv = new File(path);
            BufferedWriter bw = new BufferedWriter(new FileWriter(csv,false));
            Iterator iterator =list.iterator();
            while (iterator.hasNext()){
                line = (String)iterator.next();
                bw.write(line);
                bw.newLine();
            }
            bw.close();
            result =true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @descrption: This is a function that will change the data in local files
     * @param id   id of the customer
     * @param money  the actual value after updating
     * @return: none
     */
    public static void setMoney(int id, double money) {
        if (cash_data == null){
            cash_data = readGeneral(cashPath);
        }
        String updated ="";
        cash_data.removeIf(e ->e.matches(id+",(.*)"));
        updated = id + "," + money;
        cash_data.add(updated);
        writeGeneral(cash_data,cashPath);
    }


    /**
     * @Descrption: This is the function that will get the money amount of the user by id
     * @param id  id of the user
     * @return money the value in double. if not exist ,it will return 0
     */
    public static double getMoney(int id) {
        if (cash_data == null){
            cash_data = readGeneral(cashPath);
        }
        String line;
        String cvsSplitBy = ",";
        double cash = 0;
        Iterator iterator = cash_data.iterator();
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
