import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import exceptions.*;
import java.io.*;

public class Login extends JFrame {
    private JFrame jFrame;
    private JPanel panel = new JPanel();
    private JLabel userLabel = new JLabel("User Name:", JLabel.CENTER);  
    private JTextField userText = new JTextField();       
    private JLabel passLabel = new JLabel("Password:", JLabel.CENTER);       
    private JPasswordField passText = new JPasswordField(20); 
    private JButton loginButton = new JButton("login");       
    private JButton registerButton = new JButton("register"); 
    private JLabel title = new JLabel("Digital Gym", JLabel.CENTER);
    private LoginController loginController = new LoginController();
    private CustomerSchedule customerSchedule;

    public Login(JFrame frame) {
        jFrame = frame;
        //Set the position and size of GUI window
        jFrame.setSize(800,500);
        jFrame.setLocationRelativeTo(null);                    
        jFrame.add(panel);                                  
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        placeComponents(panel);                               
        jFrame.setVisible(true);                        
    }
    
    /**
     * Layout for panel
     * @param panel
     */
    public void placeComponents(JPanel panel) {

        panel.setLayout(null);

        title.setBounds(200, 0, 400, 200);
        title.setFont(new Font("Arial", Font.BOLD, 48));
        panel.add(title);
        userLabel.setBounds(250, 100, 200, 200);
        userLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(userLabel);
        userText.setBounds(420, 190, 100, 25);
        panel.add(userText);
        passLabel.setBounds(250, 200, 200, 200);
        passLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(passLabel);
        passText.setBounds(420, 290, 100, 25);
        panel.add(passText);

        loginButton.setBounds(300, 350, 80, 25);
        LoginMonitor loginMonitor = new LoginMonitor();
        loginButton.addActionListener(loginMonitor);
        panel.add(loginButton);
        registerButton.setBounds(430, 350, 80, 25);
        RegisterMonitor registerMonitor = new RegisterMonitor();
        registerButton.addActionListener(registerMonitor);
        panel.add(registerButton);
    }

    class LoginMonitor implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                loginController.login(userText.getText(), passText.getText());
                customerSchedule = new CustomerSchedule(jFrame); // Just for test the function of switch between windows, need to be update later
                panel.setVisible(false);
                System.out.println("Login：msg"+e.getActionCommand()+" user: " + userText.getText() + " pass: " + passText.getText());
            }catch(PasswordException exception){
                System.out.println("PasswordException");
            }catch(NoMemberException exception){
                System.out.println("NoMemberException");
            }catch(IOException exception){
                System.out.println("IOException");
            }
        }
    }

    class RegisterMonitor implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Register：msg"+e.getActionCommand()+" user: " + userText.getText() + " pass: " + passText.getText());
            try{
                loginController.register(userText.getText(),  passText.getText(), User.Type.Customer);
            }catch(IllegalException exception){
                System.out.println("IllegalException");
            }catch(MemberExistedException exception){
                System.out.println("MemberExistedException");
            }catch(IOException exception){
                System.out.println("IOException");
            }
        }
    }
}


