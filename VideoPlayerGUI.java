import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel; 
/**
 * A VideoPlayer class which provide video GUI panel
 */
public class VideoPlayerGUI extends LeafGUI implements ActionListener{
	private int rowsNum = 2;
	private JButton button_send = new JButton("send");
	private JButton button_remove = new JButton("remove");
	private JTextField commentInput = new JTextField();
	private JTextArea commentArea = new JTextArea(this.getComment(),1,this.getRowsNum());
	private JLabel videoName = new JLabel("Video Title", JLabel.CENTER);
	private JLabel videoDesc = new JLabel("Video Description", JLabel.CENTER);
	private JLabel videoAuthor = new JLabel("Video Author", JLabel.CENTER);
	private JLabel videoPath = new JLabel("Video Path", JLabel.CENTER);
	private Video video;
	/**
     * Initialize GUI frame then add the CustomerSchedule panel to the frame
     * The method will attach the CustomerSchedule panel to the frame
     * @param frame the frame for display the GUI
     * @return void
     * @seeUser
     */
    public VideoPlayerGUI(Video video) {
		this.video = video;
        Font font = new Font("Dialog",Font.BOLD,16);
		button_send.setFont(font);
		videoName.setFont(font);
		videoDesc.setFont(font);
		videoAuthor.setFont(font);
		videoPath.setFont(font);
		JPanel panel_main = new JPanel();
		panel_main.setLayout(new GridLayout(2, 2, 1, 1));
		panel_main.add(videoName);
		panel_main.add(videoDesc);
		panel_main.add(videoAuthor);
		panel_main.add(videoPath);
		videoName.setText("Video Title: " + video.getTitle());
		videoDesc.setText("Video Description: " + video.getDescription());
		videoAuthor.setText("Video Author: " + video.getAuthor().getName());
		videoPath.setText("Video Path: " + video.getVideoPath());

		commentArea.setEditable(false);
		commentArea.setLineWrap(true);
		JScrollPane jsp=new JScrollPane(commentArea);
        Dimension size=commentArea.getPreferredSize();
        jsp.setBounds(110,90,size.width,size.height);
		JPanel panel_commentInput = new JPanel();
		panel_commentInput.setLayout(new GridLayout(2, 1, 1, 1));
		panel_commentInput.add(commentInput);
		panel_commentInput.add(button_send);
		button_send.addActionListener(this);
		JPanel panel_comment = new JPanel();
		panel_comment.setLayout(new GridLayout(2, 1, 1, 1));
		panel_comment.add(jsp);
		panel_comment.add(panel_commentInput);
		JPanel panel_remove = new JPanel();
		panel_remove.setLayout(new GridLayout(2,1,1,1));
		panel_remove.add(getPanel());
		if(GUIController.getUser().getClass() == Trainer.class){
			System.out.println("YES");
			button_remove.setFont(font);
			panel_remove.add(button_remove);
			button_remove.addActionListener(this);
		}
		this.setLayout(new BorderLayout());
		this.add(panel_remove,BorderLayout.NORTH);
		this.add(panel_main,BorderLayout.CENTER);
		this.add(panel_comment,BorderLayout.SOUTH);                               
    }

	private int getRowsNum(){
		return rowsNum;
	}

	private void setRowsNum(int num){
		this.rowsNum = num;
	}

	private String getComment() {
		this.setRowsNum(6);
		return "from luca: test test test\nfrom winter: 123456\nfrom luca: test test test\nfrom winter: 123456\nfrom luca: test test test\nfrom winter: 123456\n";
	}

	private void appendComment(String input) {
		this.setRowsNum(this.getRowsNum() + 1);
		commentArea.setRows(this.getRowsNum());
		commentArea.append(input + "\n");
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
		if(e.getSource()==button_send){
			System.out.println(commentInput.getText());
			this.appendComment(commentInput.getText());
		}else if(e.getSource() == button_remove){
			System.out.println(this.video.getTitle());
			if(video.getAuthor() == GUIController.getUser()){
				VideoController.removeVideo(this.video);
				GUIController.switchPage(new VideoListGUI());
			}else{
				button_remove.setText("You can only remove video uploaded by you");
			}
		}
    }
}


