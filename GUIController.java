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
    private static User user;
    
    /**
     * Initialize GUI from the login and register page and push the panel into the stack
     * The method set the frame and attach the panel of login and register to the frame
     * @param frame the frame for display the GUI
     * @return void
     *
     */
    public static void Initialize(JFrame frame){
        mainFrame = frame;
        frame.setTitle("Digital Gym System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setLocationRelativeTo(null);
        frame.setSize(800,500);
        frame.setLocation(400, 100);
        frame.setVisible(true);
        frame.setResizable(false); // Lock the size to avoid bug
        switchPage(new LoginGUI());
    }

    /**
     * <p>Switch pages and push the new page into stack
     * <p>Using this method will remove previous page from stack, 
     * making it impossible to go back to this page
     * @param to the new panel which will be set visible
     */
    public static void switchPage(JPanel to){
        JPanel from = new JPanel();
        try {
            from = PANELS.pop();
        } catch (EmptyStackException e) {
            // do nothing
        }
        from.setVisible(false);
        mainFrame.remove(from);
        mainFrame.add(to);
        to.setVisible(true);
        PANELS.push(to);
        mainFrame.repaint(); // repaint to avoid bugs in rendering
    }

    /**
     * Navigate to a new page and put it into stack
     * The method set the old panel invisible and the new panel over it
     * <p>Using this method can go back to previous page by using {@link GUIController#back()}
     * @param to the new panel which will be set visible
     * @return void
     */
    public static void navigateTo(JPanel to){
        JPanel from = new JPanel();
        try {
            from = PANELS.peek();
        } catch (EmptyStackException e) {
            // do nothing
        }
        from.setVisible(false);
        mainFrame.remove(from);
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
        JPanel from = new JPanel();
        try {
            from = PANELS.pop();
            from.setVisible(false);
            mainFrame.remove(from);
            mainFrame.add(PANELS.peek());
            PANELS.peek().setVisible(true);
            mainFrame.repaint(); // repaint to avoid bugs in rendering
        } catch (EmptyStackException e) {
            exit();
        }
        
    }

    public static void exit(){
        try {
            LoginGUI loginGUI = new LoginGUI();
            switchPage(loginGUI);
            PANELS.clear();
            PANELS.add(loginGUI);
            mainFrame.repaint(); // repaint to avoid bugs in rendering
        } catch (EmptyStackException e) {
            System.exit(0); // Close the program
        }
    }

    public static User getUser(){
        return user;
    }

    public static void setUser(User input){
        user = input;
    }
}
