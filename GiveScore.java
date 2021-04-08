import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;



public class GiveScore {
    //private static final String scorePath = "E:\\Digital_gym_system\\data\\score.csv";
    private static final String scorePath = "./data/score.csv";
    private static ArrayList<String> score_data;

    /**
     *Get the information stored in csv file
     */

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

    /**
     *Add information in csv file
     */

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
     *Crate new information for a trainer. If there already have information of this trainer. This method will change score stored in file.
     * @param id   id of the customer
     * @param score the score given by costumer
     */

    public static void changescore(int id, double score) {
        double newscore = 0, totalscore = 0, newtotal = 0;
        int scorecount = 0, oldcount = 0;
        if (score_data == null){
            score_data = readGeneral(scorePath);
        }
        oldcount = getcount(id);
        totalscore = gettotalscore(id);
        newtotal = totalscore + score;
        scorecount = oldcount + 1;
        newscore = (totalscore + score)/scorecount;
        String updated ="";
        score_data.removeIf(e ->e.matches(id+",(.*),(.*)"));
        updated = id + "," + newscore + "," + scorecount + "," + newtotal;
        score_data.add(updated);
        writeGeneral(score_data,scorePath);
    }

    /**
     *Get average score of trainer
     * @param id   id of the customer
     */

    public static double getscore(int id){
        double score = 0;
        String line;
        if (score_data == null){
            score_data = readGeneral(scorePath);
        }
        Iterator iterator = score_data.iterator();
        while (iterator.hasNext()) {
            line = (String) (iterator.next());
            String[] bot = line.split(",");
            if (Integer.parseInt(bot[0])== id)
                score = Double.parseDouble(bot[1]);
        }

        return score;
    }

    /**
     *Get number of times scored
     * @param id   id of the customer
     */

    public static int getcount(int id){
        int count = 0;
        String line;
        if (score_data == null){
            score_data = readGeneral(scorePath);
        }
        Iterator iterator = score_data.iterator();
        while (iterator.hasNext()) {
            line = (String) (iterator.next());
            String[] bot = line.split(",");
            if (Integer.parseInt(bot[0])== id)
                count = Integer.parseInt(bot[2]);
        }

        return count;
    }

    /**
     *Get total score to calculate average score
     * @param id   id of the customer
     */

    public static double gettotalscore(int id){
        double totalscore = 0;
        String line;
        if (score_data == null){
            score_data = readGeneral(scorePath);
        }
        Iterator iterator = score_data.iterator();
        while (iterator.hasNext()) {
            line = (String) (iterator.next());
            String[] bot = line.split(",");
            if (Integer.parseInt(bot[0])== id)
                totalscore = Double.parseDouble(bot[3]);
        }

        return totalscore;
    }
}
