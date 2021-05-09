import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;
/**
 * A RootGUI class which provide template for first layer GUI
 */
public class RootGUI extends JPanel{
    private JPanel panel_pages = new JPanel();
	private JButton[] buttons = {new JButton("Exit"),new JButton("Video"),new JButton("Schedule"),new JButton("Chat"),new JButton("Profile")};
	
	/**
     * Initialize GUI frame then add the CustomerSchedule panel to the frame
     * The method will attach the CustomerSchedule panel to the frame
     * @param frame the frame for display the GUI
     * @return void
     * @seeUser
     */
    public RootGUI() {
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
				VideoListGUI videoListGUI = new VideoListGUI();
				GUIController.switchPage(videoListGUI);
	
			}else if(e.getSource()==buttons[2]){
				CustomerScheduleGUI customerSchedule = new CustomerScheduleGUI(); 
				GUIController.switchPage(customerSchedule);
			}else if(e.getSource()==buttons[3]){
				ChatGUI chat = new ChatGUI();
				GUIController.switchPage(chat);
			}else if(e.getSource()==buttons[4]){
				ProfileGUI profile = new ProfileGUI();
				GUIController.switchPage(profile);
			}
		}
	}
}


