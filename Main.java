import javax.swing.*;

public class Main extends JFrame{
    private static final long serialVersionUID = 2432L; //Implementing serializable interface
    private JPanel panel = new JPanel();
    private Login login;
    public Main(){
        this.setTitle("Digital Gym System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,500);
        this.setLocation(400, 100);
        this.setVisible(true);
        this.login = new Login(this);
    }

    //Method to run the program
    public static void main(String[] args) {
        Main view = new Main(); //Initialize a new Main view
    }
}