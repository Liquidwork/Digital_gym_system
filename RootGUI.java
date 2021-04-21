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
	private JButton button_pageSchedule = new JButton("Schedule");
	private JButton button_pageVideo = new JButton("Video");
	private JButton button_back = new JButton("home");
	
	/**
     * Initialize GUI frame then add the CustomerSchedule panel to the frame
     * The method will attach the CustomerSchedule panel to the frame
     * @param frame the frame for display the GUI
     * @return void
     * @seeUser
     */
    public RootGUI() {
        Font font = new Font("Dialog",Font.BOLD,16);
		button_pageSchedule.setFont(font);
		button_pageVideo.setFont(font);
		button_back.setFont(font);   
		button_pageSchedule.add(button_back);   
		panel_pages.add(button_pageVideo); 
		panel_pages.add(button_back); 
		PagesListener pagesListener = new PagesListener();
		button_pageSchedule.addActionListener(pagesListener);
		button_pageVideo.addActionListener(pagesListener);
		button_back.addActionListener(pagesListener);  

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
			if(e.getSource()==button_back){
				GUIController.back();
			}else if(e.getSource()==button_pageVideo){
				VideoPlayer videoPlayer = new VideoPlayer(); // Just for test the function of switch between windows, need to be update later
				GUIController.switchPage(videoPlayer.getPanel());
	
			}else if(e.getSource()==button_pageSchedule){
				CustomerSchedule customerSchedule = new CustomerSchedule(); // Just for test the function of switch between windows, need to be update later
				GUIController.switchPage(customerSchedule.getPanel());
			}
		}
	}
}


