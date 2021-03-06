import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel; 
/**
 * A VideoPlayer class which provide video GUI panel
 */
public class VideoPlayerGUI extends LeafGUI implements ActionListener{
	private CommentController commentController;
	private ArrayList<Comment> commentList;
	private int rowsNum = 2;
	private JButton button_send = new JButton("send");
	private JButton button_remove = new JButton("remove");
	private JTextField commentInput = new JTextField();
	private JTextArea commentArea = new JTextArea();
	private JLabel videoLabel = new JLabel("Video", JLabel.CENTER);
	private JLabel videoName = new JLabel("Video Title", JLabel.CENTER);
	private JLabel videoDesc = new JLabel("Video Description", JLabel.CENTER);
	private JLabel videoAuthor = new JLabel("Video Author", JLabel.CENTER);
	private JLabel videoPath = new JLabel("Video Path", JLabel.CENTER);
	private Video video;
	/**
     * Initialize GUI frame then add the VideoPlayerGUI panel to the frame
     * The method will attach the VideoPlayerGUI panel to the frame
     * @return void
     *
     */
    public VideoPlayerGUI(Video video) {
		this.video = video;
		VideoController.addView(video); // Add view count
		commentController = new CommentController(this.video);
		commentArea.setText(this.getComment());
        Font font = new Font("Dialog",Font.BOLD,16);
		button_send.setFont(font);
		videoName.setFont(font);
		videoDesc.setFont(font);
		videoAuthor.setFont(font);
		videoPath.setFont(font);
		JPanel panel_main = new JPanel();
		panel_main.setLayout(new GridLayout(2, 1, 1, 1));
		JPanel panel_player = new JPanel();
		panel_player.setBorder(BorderFactory.createLineBorder(new Color(34, 98, 95), 3));
		panel_player.setBackground(new Color(34, 98, 95));
		panel_player.add(videoLabel);
		JPanel panel_info = new JPanel();
		panel_info.setLayout(new GridLayout(2, 2, 1, 1));
		panel_info.add(videoName);
		panel_info.add(videoDesc);
		panel_info.add(videoAuthor);
		panel_info.add(videoPath);
		panel_main.add(panel_player);
		panel_main.add(panel_info);
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
		panel_remove.setLayout(new GridLayout(3,1,1,1));
		panel_remove.add(getPanel());
		if(GUIController.getUser().getClass() == Trainer.class || GUIController.getUser().getClass() == Admin.class){
			button_remove.setFont(font);
			panel_remove.add(button_remove);
			button_remove.addActionListener(this);
			JLabel viewLabel = new JLabel("View: " + VideoController.getView(video));
			viewLabel.setHorizontalAlignment(JLabel.CENTER);
			panel_remove.add(viewLabel);
		}
		this.setLayout(new GridLayout(3, 1, 1, 1));
		this.add(panel_remove);
		this.add(panel_main);
		this.add(panel_comment);
		
		KeyListener keyListener = new KeyListener(){ // Initialize a keyListener that listen for 'enter' and work as send
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    VideoPlayerGUI.this.button_send.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Do nothing
                
            }

            @Override
            public void keyTyped(KeyEvent e) {
                // Do nothing
            }
        };
		this.commentInput.addKeyListener(keyListener); // Pressing enter will send the message immediately
    }

	/**
	 * The getter function for RowsNum
	 * @return int rowsNum current rows of comment area
	 */
	private int getRowsNum(){
		return rowsNum;
	}

	/**
	 * The setter function for RowsNum
	 * @param int num the number of rows will be in the comment area
	 */
	private void setRowsNum(int num){
		this.rowsNum = num;
	}

	/**
	 * The getter function for comment
	 * @return String commentString current comment string of comment area
	 */
	private String getComment() {
		commentList = commentController.getComments();
		this.setRowsNum(commentList.size());
		String commentString = "";
		for(Comment comment:commentList){
			commentString += "from " + comment.getAuthor().getName() + ": " + comment.getComment() + "\n";
		}
		commentArea.setLineWrap(true);
		commentArea.setRows(this.getRowsNum());
		return commentString;
	}

	/**
     * The method will append new comment to comment area text
     * @return void
     * @param JTextField commentInput the input of comment
     */
	private void appendComment(JTextField commentInput) {
		if(commentInput.getText().length() > 0){
			this.setRowsNum(this.getRowsNum() + 1);
			commentArea.setRows(this.getRowsNum());
			commentArea.append("from " + GUIController.getUser().getName() + ": " + commentInput.getText() + "\n");
			commentController.sendComments(GUIController.getUser(), commentInput.getText());
			commentInput.setText("");
		}
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
		if(e.getSource()==button_send){
			System.out.println(commentInput.getText());
			this.appendComment(commentInput);
		}else if(e.getSource() == button_remove){
			System.out.println(this.video.getTitle());
			if(video.getAuthor() == GUIController.getUser() || GUIController.getUser().getClass() == Admin.class){
				VideoController.removeVideo(this.video);
				GUIController.switchPage(new VideoListGUI());
			}else{
				button_remove.setText("You can only remove video uploaded by you");
			}
		}
    }
}


