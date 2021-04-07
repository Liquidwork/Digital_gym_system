import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;


public class CashDB {
    private static final String cashPath = "./data/cash.csv";
    private ArrayList<String> cash_data;

    public CashDB(){
        this.cash_data = readGeneral(cashPath);
    }

    private ArrayList<String> readGeneral(String path){
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

    private boolean writeGeneral(ArrayList<String> list, String path){
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
     * @Descrption: This is a function that will change the data in local files
     * @Param: id for users, and the money you want to save
     * @retun: none
     */
    public void setMoney(int id, double money) {
        String updated ="";
        cash_data.removeIf(e ->e.matches(id+",(.*)"));
        updated = id + "," + money;
        cash_data.add(updated);
        writeGeneral(cash_data,cashPath);
    }


    /**
     * @Descrption: This is the function that will get the money amount of the user
     * you should only use it when you create a Customer object
     * @Param: id for the id
     * @retun: double the money. if not exist ,it will return 0
     */
    public double getMoney(int id) {
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

    public static void main(String[] args) {
        CashDB test = new CashDB();
        System.out.println(test.getMoney(4));
        test.setMoney(4,37);
        System.out.println(test.getMoney(4));

    }
}
