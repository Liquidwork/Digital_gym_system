import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main extends JFrame{

    private static final long serialVersionUID = 2432L; //Implementing serializable interface


    public Main(){
        this.setTitle("Digital Gym System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(800,500);
        this.setLocation(400, 100);
        this.setVisible(true);
        JLabel title = new JLabel("Digital Gym", JLabel.CENTER);
        title.setBounds(200, 100, 400, 200);
        title.setFont(new Font("Arial", Font.BOLD, 48));
        this.add(title);
    }

    //Method to run the program
    public static void main(String[] args) {
        Main view = new Main(); //Initialize a new Main view
    }
}