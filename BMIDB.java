import java.util.ArrayList;
import java.util.Iterator;

public class BMIDB {
    private static final String dataPath="./data/BMI.csv";
    private static ArrayList<BMI> BMIList;

    /**
     * This is function uesd by controller to get data
     * @return the list containing data
     */
    public static synchronized ArrayList<BMI> getBmis(){
        if(BMIList==null){
            BMIList = initBMIList();
        }
        return BMIList;
    }

    /**
     * @description this function can either add new BMI or change the existing one to local,
     * just make sure the id of video is matched
     * @param BMI the new BMI object you want to set
     */
    public static synchronized void addBMI(BMI bmi){
        if(BMIList==null){
            BMIList = initBMIList();
        }
        boolean exsited = BMIList.removeIf(e ->e.getUser().getId()==bmi.getUser().getId());
        BMIList.add(bmi);
        if(exsited){
            ArrayList<String> strings = new ArrayList<>();
            for(BMI s : BMIList){
                String string = s.getUser().getId()+","+s.getHeight()+","+s.getWeight();
                strings.add(string);
            }
            DataHandler.write(strings, dataPath);
        }
        else{
            String string = bmi.getUser().getId()+","+bmi.getHeight()+","+bmi.getWeight();
            DataHandler.append(string, dataPath);
        }

    }

    /**
     * @description this function can remove of an existing information of BMI from local
     * @param id the id of user who want to delete the information
     */
    public static synchronized void deleBMI(User user){
        if(BMIList==null){
            BMIList = initBMIList();
        }
        boolean exsited = BMIList.removeIf(e ->e.getUser().getId()==user.getId());
        if(exsited){
            ArrayList<String> strings = new ArrayList<>();
            for(BMI s : BMIList){
                String string = s.getUser().getId()+","+s.getHeight()+","+s.getWeight();
                strings.add(string);
            }
            DataHandler.write(strings, dataPath);
        }
    }

    /**
     * @description this, is a function that will store data to memory from local file
     * private function
     */
     private static synchronized ArrayList<BMI> initBMIList(){
         ArrayList<BMI> bmis = new ArrayList<>();
         ArrayList<String> strings = DataHandler.read(dataPath);
         String line;
         String cvsSplitBy=",";
         Iterator<String> iterator=strings.iterator();
         while (iterator.hasNext()){
             line=(String)(iterator.next());
             String[] element = line.split(cvsSplitBy);
             User user = UserController.getUserById(Integer.parseInt(element[0]));
             BMI bmi = new BMI(user, Double.parseDouble(element[1]), 
             Double.parseDouble(element[2]));
             bmis.add(bmi);
         }
         return bmis;

     }

     public static void main(String[] args) {
         ArrayList<BMI> test = BMIDB.initBMIList();
         System.out.println(test.size());
         //System.out.println(test.get(0));
         User user1 = new Customer(2,"luca");
         User user2 = new Customer(3,"Umihara");
         BMI bmi = new BMI(user1, 150, 58);
         BMIDB.addBMI(bmi);
         BMIDB.deleBMI(user2);
     }
}
