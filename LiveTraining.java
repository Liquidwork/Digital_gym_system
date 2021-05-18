import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * Entity class that indicate properties of a live-training.
 */
public class LiveTraining {
    private Date date;
    private int time; // time of training, from 1 to 6
    private Trainer trainer;
    private Customer customer;
    private String path;
    private static Random random = new Random();

    /**
     * Construct entity class with all properties of a live-training
     * @param date date to have the training
     * @param time scaling from 1 to 6 (included)
     * @param trainer trainer to teach this live course
     * @param customer customer to attend this course
     * @throws RuntimeException if time is out of bound
     */
    public LiveTraining(Date date, int time, Trainer trainer, Customer customer) throws RuntimeException {
        if (time < 1 && time > 6){
            throw new RuntimeException("time out of bound.");
        }
        this.date = date;
        this.time = time;
        this.trainer = trainer;
        this.customer = customer;
        this.path = random.nextInt(1000000000) + ""; // A randomly generated 9-digits integer to string
    }

    public Date getDate() {
        return this.date;
    }

    public int getTime() {
        return this.time;
    }

    public Trainer getTrainer() {
        return this.trainer;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    /**
     * A Random generated <i>Zoom</i> id
     * @return the id in string
     */
    public String getPath() {
        return this.path;
    }

    @Override
    public String toString() {
        return "{" +
            " date='" + getDate() + "'" +
            ", time='" + getTime() + "'" +
            ", trainer='" + getTrainer() + "'" +
            ", customer='" + getCustomer() + "'" +
            ", path='" + getPath() + "'" +
            "}";
    }

    public static void main(String[] args) {
        System.out.println(new LiveTraining(new GregorianCalendar(2020, 4, 20).getTime(), 5,
                 (Trainer) UserController.getUserById(3), (Customer) UserController.getUserById(2)));
    }

}
