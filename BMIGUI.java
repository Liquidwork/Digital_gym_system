import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel; 
/**
 * A BMIGUI class which provide BMI setting GUI panel
 */
public class BMIGUI extends LeafGUI implements ActionListener{
    private JLabel heightLabel = new JLabel("Your Height: ", JLabel.CENTER);
    private JLabel weightLabel = new JLabel("Your Weight: ", JLabel.CENTER);
    private JTextField heightField = new JTextField();
    private JTextField weightField = new JTextField();
    private JButton button_submit = new JButton("SUBMIT");
    private JButton button_reset = new JButton("reset");
	/**
     * Initialize GUI frame then add the BMIGUI panel to the frame
     * The method will attach the BMIGUI panel to the frame
     * @return void
     *
     */
    public BMIGUI() {
        Font font = new Font("Dialog",Font.BOLD,16);
		JPanel panel_main = new JPanel();
		panel_main.setLayout(new GridLayout(2, 2, 1, 1));
		JPanel panel_buttons = new JPanel();
		panel_buttons.setLayout(new GridLayout(1, 2, 1, 1));
		panel_main.add(heightLabel);
        panel_main.add(heightField);
        panel_main.add(weightLabel);
        panel_main.add(weightField);
        panel_buttons.add(button_submit);
        panel_buttons.add(button_reset);
        button_submit.addActionListener(this);
        button_reset.addActionListener(this);
		this.setLayout(new BorderLayout());
        this.add(getPanel(), BorderLayout.NORTH);
		this.add(panel_main, BorderLayout.CENTER);
        this.add(panel_buttons, BorderLayout.SOUTH);
		
		KeyListener keyListener = new KeyListener(){ // Initialize a keyListener that listen for 'enter' and work as send
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    BMIGUI.this.button_submit.doClick();
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
    }

    @Override
	/**
    * Provide response for different actions of calender
    * The method will response according to different action event source
     * @param e the action event
     * @return void
     *
     */
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(button_submit)){
            try{
                BMIController.addBMI(GUIController.getUser(), Double.parseDouble(heightField.getText()), Double.parseDouble(this.weightField.getText()));
                GUIController.back();
                GUIController.switchPage(new ProfileGUI());
            }catch(Exception ex){
                heightField.setText("Input might be invalid, please input your height in centimeter");
                weightField.setText("Input might be invalid, please input your height in kilogram");
            }
        }else if(e.getSource().equals(button_reset)){
            heightField.setText("");
            weightField.setText("");
        }
    }
}


