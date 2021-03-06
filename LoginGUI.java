import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import exceptions.*;

/**
 * A Login class which provide Login GUI panel
 */
public class LoginGUI extends JPanel{
    private JLabel userLabel = new JLabel("Username:", JLabel.CENTER);  
    private JTextField userText = new JTextField();       
    private JLabel passLabel = new JLabel("Password:", JLabel.CENTER);       
    private JPasswordField passText = new JPasswordField(20); 
    private JLabel alertLabel = new JLabel("", JLabel.CENTER);
    private JButton loginButton = new JButton("login");       
    private JButton registerButton = new JButton("register"); 
    private JLabel title = new JLabel("Digital Gym", JLabel.CENTER);
    private ProfileGUI profileGUI;

    /**
     * Initialize GUI frame then add the login panel to the frame
     * The method will attach the login and register panel to the frame
     */
    public LoginGUI() {
        this.setLayout(null);
        this.setBounds(0, 0, 800, 500);
        title.setBounds(200, 0, 400, 200);
        title.setFont(new Font("Arial", Font.BOLD, 48));
        this.add(title);
        userLabel.setBounds(250, 100, 200, 200);
        userLabel.setFont(new Font("Arial", Font.BOLD, 24));
        this.add(userLabel);
        userText.setBounds(420, 190, 100, 25);
        this.add(userText);
        passLabel.setBounds(250, 150, 200, 200);
        passLabel.setFont(new Font("Arial", Font.BOLD, 24));
        this.add(passLabel);
        alertLabel.setBounds(300, 300, 200, 200);
        alertLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        alertLabel.setForeground(Color.RED);
        this.add(alertLabel);
        passText.setBounds(420, 240, 100, 25);
        this.add(passText);

        loginButton.setBounds(300, 300, 80, 25);
        LoginMonitor loginMonitor = new LoginMonitor();
        loginButton.addActionListener(loginMonitor);
        this.add(loginButton);
        registerButton.setBounds(430, 300, 80, 25);
        RegisterMonitor registerMonitor = new RegisterMonitor();
        registerButton.addActionListener(registerMonitor);
        this.add(registerButton);

        // Bind enter in textbox with pressing the login button
        KeyListener keyListener = new KeyListener(){
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    LoginGUI.this.loginButton.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Do nothing
                
            }

            @Override
            public void keyTyped(KeyEvent e) {
                // Do nothing
            }
        };
        passText.addKeyListener(keyListener);
        userText.addKeyListener(keyListener);
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
         *
         */
        @Override
        @SuppressWarnings("Deprecation")
        public void actionPerformed(ActionEvent e) {
            try{
                GUIController.setUser(LoginController.login(userText.getText(), passText.getText()));
                profileGUI = new ProfileGUI();
                GUIController.switchPage(profileGUI);
                alertLabel.setText("");
                //System.out.println("Login: msg"+e.getActionCommand()+" user: " + userText.getText() + " pass: " + passText.getText());
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
         *
         */
        public void actionPerformed(ActionEvent e) {
            //System.out.println("Register:msg"+e.getActionCommand()+" user: " + userText.getText() + " pass: " + passText.getText());
            try{
                LoginController.register(userText.getText(),  passText.getText(), User.Type.Customer);
                alertLabel.setText("Register success!");
            }catch(IllegalException exception){
                alertLabel.setText(exception.getMessage());
            }catch(MemberExistedException exception){
                alertLabel.setText(exception.getMessage());
            }
        }
    }
}


