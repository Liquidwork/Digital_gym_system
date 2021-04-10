public class ScoreController {

    /**
     *A method to change the information of score based on the score gaven
     * It will read the id of the trainer given
     * The information about score will change based on score you give
     * @param trainer is trainer object
     * @param score is score given by customer
     */
    public static void score(Trainer trainer,double score){
        int id = trainer.getId();
        GiveScore.changescore(id,score);
    }

    /**
     *This part of code is used to test
     */

    public static void main(String arg[]){
        Trainer trainer = new Trainer(1,"bot",User.Type.Trainer,4,1);
        score(trainer,1);
        //double score = GiveScore.gettotalscore(1);
        double score = GiveScore.getscore(1);
        System.out.println(score);
    }
}
