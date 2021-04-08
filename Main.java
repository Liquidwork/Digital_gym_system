import javax.swing.*;

public class Main extends JFrame{
    private static final long serialVersionUID = 2432L; //Implementing serializable interface
    private JPanel panel = new JPanel();
    private Login login = new Login(this);
    public Main(){
        this.setTitle("Digital Gym System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(800,500);
        this.setLocation(400, 100);
        this.setVisible(true);
    }

    //Method to run the program
    public static void main(String[] args) {
        Main view = new Main(); //Initialize a new Main view
    }
}