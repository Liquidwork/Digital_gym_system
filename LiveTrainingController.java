import java.util.ArrayList;
import java.util.Date;

import exceptions.OutOfTimeException;

/**
 * {@code LiveTrainingController} to search for, operate the live-training course and manage the system for a curtain date.
 */
public class LiveTrainingController {
    private Date date;
    private ArrayList<LiveTraining> trainingList;

    /**
     * Initiate a {@code LiveTrainingController} with a curtain date.
     * @param date the date that live-training to be checked
     */
    public LiveTrainingController(Date date){
        this.date = date;
        // TODO: Initialize the list of livetrainings
    }

    /**
     * Initiate a {@code LiveTrainingController} for today's live-training 
     */
    public LiveTrainingController(){
        this(new Date());
    }

    /**
     * Get the live-training list.
     * @return a copy of training list of that day
     */
    public ArrayList<LiveTraining> getTrainingList(){
        return new ArrayList<>(this.trainingList);
    } 
    
    public ArrayList<LiveTraining> getListByUser(User user){
        // TODO: implement
        return null;
    }

    /**
     * Add a new live-training to this date
     * @param trainer trainer to teach this course
     * @param customer customer to attend this course
     * @param time the time of the course, from 1 to 6 (inclusive)
     * @return true if a item added
     * @exception OutOfTimeException thrown if try to book a course to the days before today
     */
    public boolean addLiveTraining(Trainer trainer, Customer customer, int time){
        if (this.date.before(new Date())){ // If the time has passed for operating
            throw new OutOfTimeException("The time of booking a course has passed.");
        }
        // TODO: implement
        return false;
    }

    /**
     * Try to remove a live training record from both local file and list.
     * @param livetraining the live-training to be remove
     * @return true if a item removed
     * @exception OutOfTimeException thrown if try to remove course to the days before today
     */
    public boolean removeTraining(LiveTraining livetraining){
        if (this.date.before(new Date())){ // If the time has passed for operating
            throw new OutOfTimeException("The time of arraging has passed.");
        }
        // TODO: implement
        return false;
    }
}
