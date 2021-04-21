import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;
/**
 * A CustomerSchedule class which provide Login GUI panel
 */
public class RootGUI{
    private JPanel panel_pages = new JPanel();
	private JButton[] buttons = {new JButton("home"),new JButton("Video"),new JButton("Schedule"),new JButton("Chat")};
	private JButton button_pageChat = new JButton("Chat");
	private JButton button_pageSchedule = new JButton("Schedule");
	private JButton button_pageVideo = new JButton("Video");
	private JButton button_home = new JButton("home");
	
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
		/* button_pageChat.setFont(font);
		button_pageSchedule.setFont(font);
		button_pageVideo.setFont(font);
		button_home.setFont(font);   
		panel_pages.add(button_pageChat); 
		panel_pages.add(button_pageSchedule);   
		panel_pages.add(button_pageVideo); 
		panel_pages.add(button_home); 
		button_pageChat.addActionListener(pagesListener);
		button_pageSchedule.addActionListener(pagesListener);
		button_pageVideo.addActionListener(pagesListener);
		button_home.addActionListener(pagesListener);   */

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
				LoginGUI login = new LoginGUI();
				GUIController.switchPage(login.getPanel());
			}else if(e.getSource()==buttons[1]){
				VideoPlayerGUI videoPlayer = new VideoPlayerGUI(); // Just for test the function of switch between windows, need to be update later
				GUIController.switchPage(videoPlayer.getPanel());
	
			}else if(e.getSource()==buttons[2]){
				CustomerScheduleGUI customerSchedule = new CustomerScheduleGUI(); // Just for test the function of switch between windows, need to be update later
				GUIController.switchPage(customerSchedule.getPanel());
			}else if(e.getSource()==buttons[3]){
				ChatGUI chat = new ChatGUI(); // Just for test the function of switch between windows, need to be update later
				GUIController.switchPage(chat.getPanel());
			}
		}
	}
}


