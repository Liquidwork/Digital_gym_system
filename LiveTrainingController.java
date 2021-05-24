import java.util.ArrayList;
import java.util.*;

import exceptions.OutOfTimeException;

/**
 * {@code LiveTrainingController} to search for, operate the live-training course and manage the system for a curtain date.
 */
public class LiveTrainingController {
    private Date date;
    private LiveTrainingDB dataList;

    /**
     * Initiate a {@code LiveTrainingController} with a curtain date.
     * @param date the date that live-training to be checked
     */
    public LiveTrainingController(Date date){
        this.date = date;
        this.dataList = new LiveTrainingDB(date);
    }

    /**
     * Initiate a {@code LiveTrainingController} for today's live-training 
     */
    public LiveTrainingController(){
        this.dataList = new LiveTrainingDB(new Date());
    }

    /**
     * Get the live-training list.
     * @return a copy of training list of that day
     */
    public ArrayList<LiveTraining> getTrainingList(){
        return new ArrayList<>(dataList.getLive());
    } 
    
    public ArrayList<LiveTraining> getListByUser(User user){
        ArrayList<LiveTraining> data = dataList.getLive();
        if (user instanceof Customer){
            data.removeIf(e-> e.getCustomer().getId() != user.getId());
        }else if (user instanceof Trainer){
            data.removeIf(e-> e.getTrainer().getId() != user.getId());
        }
        return data;
    }

    /**
     * Add a new live-training to this date
     * @param trainer trainer to teach this course
     * @param customer customer to attend this course
     * @param time the time of the course, from 1 to 5 (inclusive)
     * @return true if an item added
     * @exception OutOfTimeException thrown if try to book a course to the days before today
     */
    public void addLiveTraining(Trainer trainer, Customer customer, int time){
        LiveTraining liveTraining = new LiveTraining(date, time, trainer, customer);
        ArrayList<LiveTraining> data = new ArrayList<>(this.dataList.getLive());
        boolean state1 = data.removeIf(e->  e.getTime()==liveTraining.getTime()&&
        e.getCustomer().equals(liveTraining.getCustomer()));
        boolean state2 = data.removeIf(e->  e.getTime()==liveTraining.getTime()&&
        e.getTrainer().equals(liveTraining.getTrainer()));
        if (this.date.before(new Date())){ // If the time has passed for operating
            throw new OutOfTimeException("The time of booking this course has passed.");
        }else if(state1){
            throw new RuntimeException("You have booked at this time");
        }else if(state2){
            throw new RuntimeException("This trainer has been booked at this time");
        }else{
            dataList.addLive(liveTraining);
        }
    }

    /**
     * Try to remove a live training record from both local file and list.
     * @param livetraining the live-training to be remove
     * @return true if a item removed
     * @exception OutOfTimeException thrown if try to remove course to the days before today
     */
    public boolean removeTraining(LiveTraining livetraining){
        boolean a;
        if (this.date.before(new Date())){ // If the time has passed for operating
            throw new OutOfTimeException("The time of arraging has passed.");
        }else{
            dataList.rmvLive(livetraining);
            a = true;
        }
        return a;
    }

    public static void main(String arg[]){
        Date tDate = new GregorianCalendar(2022, 4, 20).getTime();
        LiveTrainingController liveTrainingController = new LiveTrainingController(tDate);
        //liveTrainingController.addLiveTraining((Trainer)UserController.getUserById(3), (Customer)UserController.getUserById(2), 4);
        liveTrainingController.addLiveTraining((Trainer)UserController.getUserById(3), (Customer)UserController.getUserById(4), 4);
        //liveTrainingController.removeTraining(liveTrainingController.getListByUser(UserController.getUserById(3)).get(0));
        //System.out.println(liveTrainingController.getTrainingList());
        //System.out.println(liveTrainingController.getListByUser(UserController.getUserById(4)));
    }
}
