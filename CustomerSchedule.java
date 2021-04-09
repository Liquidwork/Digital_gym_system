import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerSchedule extends JFrame {
    private JFrame jFrame;
    private JPanel panel = new JPanel();
    private JLabel userLabel = new JLabel("Test:", JLabel.CENTER);  
    private JTextField userText = new JTextField();       
    private JLabel passLabel = new JLabel("Test:", JLabel.CENTER);       
    private JPasswordField passText = new JPasswordField(20); 
    private JButton loginButton = new JButton("Button1");       
    private JButton registerButton = new JButton("Button2");
    private JLabel title = new JLabel("Test", JLabel.CENTER);
    private LoginController loginController = new LoginController();

    public CustomerSchedule(JFrame frame) {
        //设置窗体的位置及大小
        jFrame = frame;
        //设置窗体的位置及大小
        jFrame.setSize(800,500);
        jFrame.setLocationRelativeTo(null);                    
        jFrame.add(panel);                                  
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        placeComponents(panel);                               
        jFrame.setVisible(true);                     
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
            panel.setVisible(false);
            System.out.println("Login：msg"+e.getActionCommand()+" user: " + userText.getText() + " pass: " + passText.getText());
            loginController.login(userText.getText(), passText.getText());
        }
    }

    class RegisterMonitor implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Register：msg"+e.getActionCommand()+" user: " + userText.getText() + " pass: " + passText.getText());
            loginController.register(userText.getText(), passText.getText());
        }
    }
}

