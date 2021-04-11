import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * A GUI controller which provide methods to access GUI pages 
 */
public class GUIController {
    // The stack for store panels, the top of stack is the panel which is currently display
    private static Stack<JPanel> PANELS=new Stack<JPanel>();
    private static JFrame mainFrame;

    /**
     * Initialize GUI from the login and register page and push the panel into the stack
     * The method set the frame and attach the panel of login and register to the frame
     * @param frame the frame for display the GUI
     * @return void
     * @seeUser
     */
    public static void Initialize(JFrame frame){
        mainFrame = frame;
        frame.setTitle("Digital Gym System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setLocationRelativeTo(null);
        frame.setSize(800,500);
        frame.setLocation(400, 100);
        frame.setVisible(true);
        switchPage(new Login().getPanel());
    }

    /**
     * Switch pages for GUI and push the new page into stack
     * The method set the old panel invisible and the new panel visible
     * @param to the new panel which will be set visible
     * @return void
     */
    public static void switchPage(JPanel to){
        try {
            PANELS.peek().setVisible(false);
        } catch (EmptyStackException e) {
            // do nothing
        }
        mainFrame.add(to);
        to.setVisible(true);
        PANELS.push(to);
        mainFrame.repaint(); // repaint to avoid bugs in rendering
    }

    /**
     * <p>Switch back to the last page for GUI and pop the current panel from stack
     * The method set the current panel invisible and the new panel visible
     * <p>If there is no previous page, the program will exit.
     */
    public static void back(){
        try {
            mainFrame.remove(PANELS.pop()); // It will not be found again, so it will be removed
            PANELS.peek().setVisible(true);
            mainFrame.repaint(); // repaint to avoid bugs in rendering
        } catch (EmptyStackException e) {
            System.exit(0); // Close the program
        }
    }
}