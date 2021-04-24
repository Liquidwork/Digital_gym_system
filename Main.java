import javax.swing.*;

public class Main extends JFrame{
    private static final long serialVersionUID = 2432L; //Implementing serializable interface
    public Main(){
        GUIController.Initialize(this);
    }

    //Method to run the program
    public static void main(String[] args) {
        Main view = new Main(); //Initialize a new Main view
        view.requestFocus();
    }
}