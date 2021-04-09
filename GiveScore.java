import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;



public class GiveScore {
    //private static final String scorePath = "E:\\Digital_gym_system\\data\\score.csv";
    private static final String scorePath = "./data/score.csv";
    private static ArrayList<String> score_data;


    /**
     *Crate new information for a trainer. If there already have information of this trainer. This method will change score stored in file.
     * @param id   id of the customer
     * @param score the score given by costumer
     */

    public static void changescore(int id, double score) {
        double newscore = 0, totalscore = 0, newtotal = 0;
        int scorecount = 0, oldcount = 0;
        if (score_data == null){
            score_data = DataHandler.read(scorePath);
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
        DataHandler.write(score_data,scorePath);
    }

    /**
     *Get average score of trainer
     * @param id   id of the customer
     */

    public static double getscore(int id){
        double score = 0;
        String line;
        if (score_data == null){
            score_data = DataHandler.read(scorePath);
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
            score_data = DataHandler.read(scorePath);
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
            score_data = DataHandler.read(scorePath);
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
