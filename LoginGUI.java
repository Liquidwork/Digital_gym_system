import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import exceptions.*;
import java.io.*;
/**
 * A Login class which provide Login GUI panel
 */
public class LoginGUI {
    private JPanel panel = new JPanel();
    private JLabel userLabel = new JLabel("Username:", JLabel.CENTER);  
    private JTextField userText = new JTextField();       
    private JLabel passLabel = new JLabel("Password:", JLabel.CENTER);       
    private JPasswordField passText = new JPasswordField(20); 
    private JLabel alertLabel = new JLabel("", JLabel.CENTER);
    private JButton loginButton = new JButton("login");       
    private JButton registerButton = new JButton("register"); 
    private JLabel title = new JLabel("Digital Gym", JLabel.CENTER);
    private CustomerScheduleGUI customerSchedule;

    /**
     * Initialize GUI frame then add the login panel to the frame
     * The method will attach the login and register panel to the frame
     */
    public LoginGUI() {
        panel.setLayout(null);
        panel.setBounds(0, 0, 800, 500);
        title.setBounds(200, 0, 400, 200);
        title.setFont(new Font("Arial", Font.BOLD, 48));
        panel.add(title);
        userLabel.setBounds(250, 100, 200, 200);
        userLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(userLabel);
        userText.setBounds(420, 190, 100, 25);
        panel.add(userText);
        passLabel.setBounds(250, 150, 200, 200);
        passLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(passLabel);
        alertLabel.setBounds(300, 300, 200, 200);
        alertLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        alertLabel.setForeground(Color.RED);
        panel.add(alertLabel);
        passText.setBounds(420, 240, 100, 25);
        panel.add(passText);

        loginButton.setBounds(300, 300, 80, 25);
        LoginMonitor loginMonitor = new LoginMonitor();
        loginButton.addActionListener(loginMonitor);
        panel.add(loginButton);
        registerButton.setBounds(430, 300, 80, 25);
        RegisterMonitor registerMonitor = new RegisterMonitor();
        registerButton.addActionListener(registerMonitor);
        panel.add(registerButton);                      
    }

    /**
     * The method is the getter of panel
     * @return Jpanel the panel of login and register page
     * @seeUser
     */
    public JPanel getPanel() {
		return panel;
	}
    /**
     * The action listener for login button
     * The class will response for exceptions thrown by login button
     */
    class LoginMonitor implements ActionListener {
        /**
         * Provide response for different results of login
         * The method will edit the text of alertLabel according to the message of exception
         * @param e the action event
         * @return void
         * @seeUser
         */
        @Override
        @SuppressWarnings("Deprecation")
        public void actionPerformed(ActionEvent e) {
            try{
                LoginController.login(userText.getText(), passText.getText());
                customerSchedule = new CustomerScheduleGUI(); // Just for test the function of switch between windows, need to be update later
                GUIController.setUsername(userText.getText());
                GUIController.switchPage(customerSchedule.getPanel());
                System.out.println("Login: msg"+e.getActionCommand()+" user: " + userText.getText() + " pass: " + passText.getText());
            }catch(PasswordException exception){
                alertLabel.setText(exception.getMessage());
            }catch(NoMemberException exception){
                alertLabel.setText(exception.getMessage());
            }
        }
    }
    /**
     * The action listener for register button
     * The class will response for exceptions thrown by register button
     */
    class RegisterMonitor implements ActionListener {
        @Override
        /**
         * Provide response for different results of register
         * The method will edit the text of alertLabel according to the message of exception
         * @param e the action event
         * @return void
         * @seeUser
         */
        public void actionPerformed(ActionEvent e) {
            System.out.println("Register:msg"+e.getActionCommand()+" user: " + userText.getText() + " pass: " + passText.getText());
            try{
                LoginController.register(userText.getText(),  passText.getText(), User.Type.Customer);
            }catch(IllegalException exception){
                alertLabel.setText(exception.getMessage());
            }catch(MemberExistedException exception){
                alertLabel.setText(exception.getMessage());
            }
        }
    }
}


