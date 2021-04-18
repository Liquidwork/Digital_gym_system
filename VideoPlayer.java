import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel; 
/**
 * A VideoPlayer class which provide video GUI panel
 */
public class VideoPlayer implements ActionListener{
    private JPanel panel = new JPanel();
	private JButton button_pageSchedule = new JButton("Schedule");
	private JButton button_back = new JButton("back");
	private JButton button_send = new JButton("send");
	private JTextField commentInput = new JTextField();
	private JLabel videoName = new JLabel("videoName", JLabel.CENTER);
	private JLabel videoDesc = new JLabel("videoDesc", JLabel.CENTER);
	private JLabel videoTime = new JLabel("videoTime", JLabel.CENTER);
	private JLabel videoUrl = new JLabel("videoUrl", JLabel.CENTER);
	/**
     * Initialize GUI frame then add the CustomerSchedule panel to the frame
     * The method will attach the CustomerSchedule panel to the frame
     * @param frame the frame for display the GUI
     * @return void
     * @seeUser
     */
    public VideoPlayer() {
        draw(panel);                                   
    }

	/**
     * Set the layout for panel
     * @param panel
     * @return void
     */
	private void draw(JPanel panel) {
		Font font = new Font("Dialog",Font.BOLD,16);
		button_pageSchedule.setFont(font);
		button_back.setFont(font);
		button_send.setFont(font);
		JPanel panel_pages = new JPanel();
		panel_pages.add(button_pageSchedule);
		button_pageSchedule.addActionListener(this);
		panel_pages.add(button_back);
		button_back.addActionListener(this);
		videoName.setFont(font);
		videoDesc.setFont(font);
		videoTime.setFont(font);
		videoUrl.setFont(font);
		JPanel panel_main = new JPanel();
		panel_main.setLayout(new GridLayout(2, 2, 1, 1));
		panel_main.add(videoName);
		panel_main.add(videoDesc);
		panel_main.add(videoTime);
		panel_main.add(videoUrl);
		JPanel panel_comment = new JPanel();
		panel_comment.setLayout(new GridLayout(1, 2, 1, 1));
		panel_comment.add(commentInput);
		panel_comment.add(button_send);
		panel.setLayout(new BorderLayout());
		panel.add(panel_pages,BorderLayout.NORTH);
		panel.add(panel_main,BorderLayout.CENTER);
		panel.add(panel_comment,BorderLayout.SOUTH);
	}

	/**
     * The method is the getter of panel
     * @return Jpanel the panel of login and register page
     * @seeUser
     */
	public JPanel getPanel() {
		return panel;
	}
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
		}else if(e.getSource()==button_pageSchedule){
			CustomerSchedule customerSchedule = new CustomerSchedule(); // Just for test the function of switch between windows, need to be update later
            GUIController.switchPage(customerSchedule.getPanel());
		}  
    }
}


