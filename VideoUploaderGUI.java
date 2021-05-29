import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel; 
/**
 * A VideoUploaderGUI class which provide VideoUploaderGUI panel
 */
public class VideoUploaderGUI extends LeafGUI implements ActionListener{
	private JButton button_upload = new JButton("upload");
	private JTextField videoName = new JTextField("Video Title", JLabel.CENTER);
	private JTextField videoDesc = new JTextField("Video Description", JLabel.CENTER);
	private JTextField videoAuthor = new JTextField(GUIController.getUser().getName(), JLabel.CENTER);
	private JTextField videoPath = new JTextField("Video Path", JLabel.CENTER);
	/**
     * Initialize GUI frame then add the VideoUploaderGUI panel to the frame
     * The method will attach the VideoUploaderGUI panel to the frame
     * @return void
     *
     */
    public VideoUploaderGUI() {
        Font font = new Font("Dialog",Font.BOLD,16);
		button_upload.setFont(font);
		button_upload.addActionListener(this);
		videoName.setFont(font);
		videoDesc.setFont(font);
		videoAuthor.setFont(font);
		videoAuthor.setEditable(false);
		videoPath.setFont(font);
		JPanel panel_main = new JPanel();
		panel_main.setLayout(new GridLayout(2, 2, 1, 1));
		panel_main.add(videoName);
		panel_main.add(videoDesc);
		panel_main.add(videoAuthor);
		panel_main.add(videoPath);
		this.setLayout(new BorderLayout());
		this.add(getPanel(),BorderLayout.NORTH);
		this.add(panel_main,BorderLayout.CENTER);
		this.add(button_upload,BorderLayout.SOUTH);                               
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
		if(e.getSource()==button_upload){
			System.out.println(videoAuthor.getText());
			VideoController.addVideo(GUIController.getUser(),videoName.getText(), videoPath.getText(), videoDesc.getText());
			GUIController.back(); // First get back to last page to avoid dead stack
			GUIController.switchPage(new VideoListGUI());
		} 
    }
}


