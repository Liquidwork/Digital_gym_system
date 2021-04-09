import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;



public class ScoreController {
    private static final String scorePath = "F:\\Digital_gym_system\\data\\score.csv";
    //private static final String scorePath = "./data/score.csv";
    private static ArrayList<String> score_data;


    /**
     *Crate new information for a trainer. If there already have information of this trainer. This method will change score stored in file.
     * @param id   id of the customer
     * @param score the score given by costumer
     */
    public static void addNewScore(int id, double score) {
        double totalscore = 0, newtotal = 0;
        int scorecount = 0, oldcount = 0;
        if (score_data == null){
            score_data = DataHandler.read(scorePath);
        }
        oldcount = getCount(id);
        totalscore = getTotalScore(id);
        newtotal = totalscore + score;
        scorecount = oldcount + 1;
        String updated ="";
        score_data.removeIf(e ->e.matches(id+",(.*),(.*)"));
        updated = id +"," + scorecount + "," + newtotal;
        score_data.add(updated);
        DataHandler.write(score_data,scorePath);
    }

    /**
     *Get average score of trainer
     * @param id   id of the customer
     */

    public static double getAverageScore(int id){
        double score = 0;
        int count = getCount(id);
        double total = getTotalScore(id);
        score = total/count;

        return score;
    }

    /**
     *Get number of times scored
     * @param id   id of the customer
     */

    public static int getCount(int id){
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
                count = Integer.parseInt(bot[1]);
        }

        return count;
    }

    /**
     *Get total score to calculate average score
     * @param id   id of the customer
     */

    public static double getTotalScore(int id){
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
                totalscore = Double.parseDouble(bot[2]);
        }

        return totalscore;
    }


    /**
     * Test method
     * @param arg
     */
    public static void main(String arg[]){
        double score;
        int id;
        id = 1;
        addNewScore(id,5);
        score = getAverageScore(id);
        System.out.println(score);
    }
}