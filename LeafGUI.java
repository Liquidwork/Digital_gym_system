import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;
/**
 * A LeafGUI class which provide template for non-first layer GUI
 */
public class LeafGUI{
    private JPanel panel_pages = new JPanel();
	private JButton[] buttons = {new JButton("Exit"),new JButton("Back")};
	
	/**
     * Initialize GUI frame then add the CustomerSchedule panel to the frame
     * The method will attach the CustomerSchedule panel to the frame
     * @param frame the frame for display the GUI
     * @return void
     * @seeUser
     */
    public LeafGUI() {
        Font font = new Font("Dialog",Font.BOLD,16);
		PagesListener pagesListener = new PagesListener();
		for(int i = 0; i < buttons.length; i++){
			buttons[i].setFont(font);
			panel_pages.add(buttons[i]);
			buttons[i].addActionListener(pagesListener);
		}

    }

	/**
     * The method is the getter of panel
     * @return Jpanel the panel of login and register page
     * @seeUser
     */
	public JPanel getPanel() {
		return panel_pages;
	}

	private class PagesListener implements ActionListener{
		@Override
		/**
		* Provide response for different actions of calender
		* The method will response according to different action event source
		 * @param e the action event
		 * @return void
		 * @seeUser
		 */
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==buttons[0]){
				GUIController.exit();
			}else if(e.getSource()==buttons[1]){
				GUIController.back();
			}
		}
	}
}


