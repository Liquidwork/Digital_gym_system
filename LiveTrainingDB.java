
import java.text.*;
import java.util.*;

public class LiveTrainingDB {
    public String path ="./data/live/" ;
    private Date date;
    private ArrayList<LiveTraining> lives = new ArrayList<>();

    /**
     * A class that will read from local file acooding to date required
     * @param date choose which day info you want 
     */
    public LiveTrainingDB(Date date){
        this.date =date;
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        path = path +ft.format(this.date)+".csv";
        // System.out.println(path);
        initLives();     
    }

    /**
     * A function for controller to get all information 
     * @return Arraylist that contaiin all live training info
     */
    public synchronized ArrayList<LiveTraining> getLive(){
        return this.lives;
    }

    /**
     * A funciton used to add new live traing info to local and JVM
     * For controller
     * @param liveT
     */
    public synchronized void addLive(LiveTraining liveT){
        int j =0;
        String line =liveT.getTime()+","+liveT.getTrainer().getId()+","+liveT.getCustomer().getId();
        DataHandler.append(line, path);
        for (LiveTraining i: this.lives) {
            if(liveT.getTime()<i.getTime()){
                lives.add(j, liveT);
                return;
            }
            else if(liveT.getTime()==i.getTime()){
                if(liveT.getTrainer().getId()<=i.getTrainer().getId()){
                    lives.add(j, liveT);
                    return;
                }                 
            }
            j++; 
        }
        lives.add(liveT);
    }

    /**
     * A function to cancel a live training 
     * @param liveT the obj you want to remove
     * @return true if remove success. fal, if it dosen't exist
     */
    public synchronized boolean rmvLive(LiveTraining liveT){
        boolean state = lives.removeIf(e->  e.getTime()==liveT.getTime()&&
        e.getCustomer().equals(liveT.getCustomer())&&
        e.getTrainer().equals(liveT.getTrainer()));
        if(state){//remove livetraining info success, need to change local data
            ArrayList<String> tmp = new ArrayList<>();
            for (LiveTraining i : lives) {
                String line  =i.getTime()+","+i.getTrainer().getId()+","+i.getCustomer().getId();
                tmp.add(line);
            }
            DataHandler.write(tmp, path);
        }
        return state;
    }

    /**
     * A init function used to load from local data and sort them
     * Only used for object creation
     */
    private synchronized void initLives(){
        ArrayList<String> data = DataHandler.read(path);
        String line;
        String cvsSplitBy = ",";
        Iterator<String> iterator = data.iterator();
        while (iterator.hasNext()) {
            line = (String) (iterator.next());
            String[] ele = line.split(cvsSplitBy);
            int time =Integer.parseInt(ele[0]);
            int coachID = Integer.parseInt(ele[1]);
            int custID = Integer.parseInt(ele[2]);
            this.lives.add(new LiveTraining(this.date, time, 
           (Trainer)UserController.getUserById(coachID), (Customer)UserController.getUserById(custID)));
        }
        Collections.sort(this.lives, new Comparator<LiveTraining>(){
            @Override
            public int compare(LiveTraining l1, LiveTraining l2){
                if  (l1.getTime() > l2.getTime()){//sort by time from small to big
                    return 1;
                }
                else if (l1.getTime() == l2.getTime()) {
                        if (l1.getTrainer().getId() > l1.getTrainer().getId()) //second priority by Trainer Id
                            return 1;
                        else return -1;
                }
                else return -1;
            }
        });
    }


    public static void main(String[] args) {
        Date tDate = new GregorianCalendar(2020, 4, 20).getTime();
        System.out.println(tDate);
        LiveTrainingDB alpha = new LiveTrainingDB(tDate);
        System.out.println(alpha.lives.size());
        for(LiveTraining i: alpha.lives){
            System.out.println(i.toString());
        }
        System.out.println("Adding new");
        LiveTraining beta = new LiveTraining(tDate, 4, (Trainer)UserController.getUserById(3), 
        (Customer)UserController.getUserById(5));
        LiveTraining gama = new LiveTraining(tDate, 1, (Trainer)UserController.getUserById(6), 
        (Customer)UserController.getUserById(4));
        alpha.addLive(beta);
        alpha.addLive(gama);
        for(LiveTraining i: alpha.lives){
            System.out.println(i.toString());
        }
        boolean show = alpha.rmvLive(gama);
        System.out.println("remove is"+show);
        for(LiveTraining i: alpha.lives){
            System.out.println(i.toString());
        }
    }

}