import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * A GUI controller which provide methods to access GUI pages 
 */
public class GUIController {
    // The stack for store panels, the top of stack is the panel which is currently display
    private static final Stack<JPanel> PANELS=new Stack<JPanel>();

    /**
     * Initialize GUI from the login and register page and push the panel into the stack
     * The method set the frame and attach the panel of login and register to the frame
     * @param frame the frame for display the GUI
     * @return void
     * @seeUser
     */
    public static void Initialize(JFrame frame){
        frame.setTitle("Digital Gym System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,500);
        frame.setLocation(400, 100);
        frame.setVisible(true);
        PANELS.push(new Login(frame).getPanel());
    }

    /**
     * Switch pages for GUI and push the new page into stack
     * The method set the old panel invisible and the new panel visible
     * @param from the old panel which will be set invisible
     * @param to the new panel which will be set visible
     * @return void
     * @seeUser
     */
    public static void switchPage(JPanel from, JPanel to){
        from.setVisible(false);
        to.setVisible(true);
        PANELS.push(to);
    }

    /**
     * Switch back to the last page for GUI and pop the current panel from stack
     * The method set the current panel invisible and the new panel visible
     * @param void
     * @return void
     * @seeUser
     */
    public static void back(){
        PANELS.pop().setVisible(false);
        PANELS.peek().setVisible(true);
    }
}
