import javax.swing.*;
import java.awt.Font;

public class Login extends JFrame {
    private JPanel panel = new JPanel();
    private JLabel userLabel = new JLabel("User Name:");  
    private JTextField userText = new JTextField();       
    private JLabel passLabel = new JLabel("Password:");       
    private JPasswordField passText = new JPasswordField(20); 
    private JButton loginButton = new JButton("login");       
    private JButton registerButton = new JButton("register"); 
    private JLabel title = new JLabel("Digital Gym", JLabel.CENTER);

    public Login(JFrame frame) {
        //设置窗体的位置及大小
        frame.setSize(800,500);
        frame.setLocationRelativeTo(null);                    
        frame.add(panel);                                  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        placeComponents(panel);                               
        frame.setVisible(true);                             
    }
    
    /**
     * 面板具体布局
     * @param panel
     */
    public void placeComponents(JPanel panel) {

        panel.setLayout(null); 

        title.setBounds(200, 0, 400, 200);
        title.setFont(new Font("Arial", Font.BOLD, 48));
        panel.add(title);
        userLabel.setBounds(50, 150, 200, 50);
        userLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(userLabel);
        userText.setBounds(205, 165, 165, 25);
        panel.add(userText);
        passLabel.setBounds(30, 60, 80, 25);
        panel.add(passLabel);
        passText.setBounds(105, 60, 165, 25);
        panel.add(passText);

        loginButton.setBounds(25, 100, 80, 25);
        panel.add(loginButton);
        registerButton.setBounds(190, 100, 80, 25);
        panel.add(registerButton);
    }
}


