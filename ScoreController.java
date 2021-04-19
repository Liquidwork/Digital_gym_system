import java.util.ArrayList;
import java.util.Iterator;


/**
 * A score controller for the trainer, providing static methods to score
 */
public class ScoreController {
    //private static final String scorePath = "F:\\Digital_gym_system\\data\\score.csv";
    private static final String scorePath = "./data/score.csv";
    private static ArrayList<String> score_data;


    /**
     * This method will add a new scoring record for the trainer.
     * Create new information for a trainer if there is no information of this trainer recorded.
     * @param trainer {@link Trainer} to be score
     * @param score the score given by customer
     */
    public static void addNewScore(Trainer trainer, double score) {
        int id = trainer.getId();
        double totalscore = 0, newtotal = 0;
        int scorecount = 0, oldcount = 0;
        if (score_data == null){
            score_data = DataHandler.read(scorePath);
        }
        oldcount = getCount(trainer);
        totalscore = getTotalScore(trainer);
        newtotal = totalscore + score;
        scorecount = oldcount + 1;
        String updated ="";
        score_data.removeIf(e ->e.matches(id+",(.*),(.*)"));
        updated = id +"," + scorecount + "," + newtotal;
        score_data.add(updated);
        DataHandler.write(score_data,scorePath);
    }

    /**
     * Get average score of trainer
     * @param trainer {@link Trainer} to be check
     */

    public static double getAverageScore(Trainer trainer){
        double score = 0;
        int count = getCount(trainer);
        double total = getTotalScore(trainer);
        score = total/count;

        return score;
    }

    /**
     * Get number of times scored
     * @param trainer {@link Trainer} to be check
     */

    public static int getCount(Trainer trainer){
        int id = trainer.getId();
        int count = 0;
        String line;
        if (score_data == null){
            score_data = DataHandler.read(scorePath);
        }
        Iterator<String> iterator = score_data.iterator();
        while (iterator.hasNext()) {
            line = (String) (iterator.next());
            String[] bot = line.split(",");
            if (Integer.parseInt(bot[0])== id)
                count = Integer.parseInt(bot[1]);
        }

        return count;
    }

    /**
     * Get total score to calculate average score
     * @param trainer {@link Trainer} to be check
     */

    public static double getTotalScore(Trainer trainer){
        int id = trainer.getId();
        double totalscore = 0;
        String line;
        if (score_data == null){
            score_data = DataHandler.read(scorePath);
        }
        Iterator<String> iterator = score_data.iterator();
        while (iterator.hasNext()) {
            line = (String) (iterator.next());
            String[] bot = line.split(",");
            if (Integer.parseInt(bot[0])== id)
                totalscore = Double.parseDouble(bot[2]);
        }

        return totalscore;
    }
}