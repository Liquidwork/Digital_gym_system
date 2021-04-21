import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Console;
import java.util.Date;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel; 
/**
 * A VideoPlayer class which provide video GUI panel
 */
public class ChatGUI extends RootGUI implements ActionListener{
	private int rowsNum = 2;
    private JPanel panel = new JPanel();
	private JButton button_send = new JButton("send");
	private JTextField commentInput = new JTextField();
	private JTextArea commentArea = new JTextArea(this.getComment(),1,this.getRowsNum());
	private JButton[] button_friend = new JButton[25];
	private String friend;
	private ChatController chatController;
	/**
     * Initialize GUI frame then add the CustomerSchedule panel to the frame
     * The method will attach the CustomerSchedule panel to the frame
     * @param frame the frame for display the GUI
     * @return void
     * @seeUser
     */
    public ChatGUI() {
        Font font = new Font("Dialog",Font.BOLD,16);
		button_send.setFont(font);
		JPanel panel_main = new JPanel();
		panel_main.setLayout(new GridLayout(5, 5, 3, 3));
		setFriend("0");
		for(int i = 0; i < button_friend.length ;i++){
			button_friend[i] = new JButton(" ");
			button_friend[i].addActionListener(this);
			panel_main.add(button_friend[i]);
		}
		this.paintFriends();
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
		panel.setLayout(null);
		super.getPanel().setBounds(0,0,800,50);
		panel_main.setBounds(0,50,800,150);
		panel_comment.setBounds(0,200,800,200);
		panel.add(super.getPanel());
		panel.add(panel_main);
		panel.add(panel_comment);                               
    }

	private void paintFriends(){
		for(int i = 0; i < button_friend.length; i++){
			button_friend[i].setText(i + " ");
		}
	}

	/**
     * The method is the getter of panel
     * @return Jpanel the panel of login and register page
     * @seeUser
     */
	public JPanel getPanel() {
		return panel;
	}

	private int getRowsNum(){
		return rowsNum;
	}

	private void setRowsNum(int num){
		this.rowsNum = num;
	}

	private String getFriend(){
		return friend;
	}

	private void setFriend(String input){
		this.friend = input;
	}

	private void setComment(ArrayList<Chat> chatList){
		String chatString = "";
		int counter = 0;
		for(Chat chat:chatController.getMessagesList()){
			if(chat.getType() == 1){
				chatString += "from " + GUIController.getUsername() + " to " + this.getFriend() + " " + chat.getMessage();
			}else{
				chatString += "from " + this.getFriend() + " to " + GUIController.getUsername() + " " + chat.getMessage();
			}
			chatString += "\n";
			counter++;
		}
		this.setRowsNum(counter);
		commentArea = new JTextArea(chatString, 1, this.getRowsNum());
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
			chatController.Send(UserController.getUserByUsername(GUIController.getUsername()), commentInput.getText());
			System.out.println(commentInput.getText());
			this.appendComment(commentInput.getText());
		}else{
			for(int i = 0; i < button_friend.length; i++){
				if(e.getSource().equals(button_friend[i])){
					chatController = new ChatController((Customer) UserController.getUserByUsername(GUIController.getUsername()), new Trainer(0, button_friend[i].getText()));
					this.setComment(chatController.getMessagesList());
					setFriend(button_friend[i].getText());
				}
			}
		}
    }
}


