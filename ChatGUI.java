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
	private int pageNum = 0;
	private final int volume = 25;
	private int haveTrainer = 0;
	private JButton button_send = new JButton("send");
	private JButton button_prev = new JButton("prev page");
	private JButton button_next = new JButton("next page");
	private JLabel current_page = new JLabel("1", JLabel.CENTER);
	private JTextField commentInput = new JTextField();
	private JTextArea commentArea = new JTextArea();
	private JButton[] button_trainer = new JButton[25];
	private Trainer trainer;
	private ArrayList<Trainer> trainersList;
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
		button_prev.setFont(font);
		button_next.setFont(font);
		JPanel panel_main = new JPanel();
		panel_main.setLayout(new GridLayout(5, 5, 3, 3));
		trainersList = UserController.getTrainersList();
		setTrainer(trainersList.get(0));
		for(int i = 0; i < button_trainer.length ;i++){
			button_trainer[i] = new JButton(" ");
			button_trainer[i].addActionListener(this);
			panel_main.add(button_trainer[i]);
		}
		this.paintTrainers(0);
		commentArea.setEditable(false);
		commentArea.setLineWrap(true);
		commentArea.setText("Please select the person you want to chat with\n");
		JScrollPane jsp=new JScrollPane(commentArea);
        Dimension size=commentArea.getPreferredSize();
        jsp.setBounds(110,90,size.width,size.height);
		JPanel panel_commentInput = new JPanel();
		panel_commentInput.setLayout(new GridLayout(2, 1, 1, 1));
		panel_commentInput.add(commentInput);
		panel_commentInput.add(button_send);
		button_send.addActionListener(this);
		JPanel panel_comment = new JPanel();
		panel_comment.setLayout(new BorderLayout());
		panel_comment.add(jsp, BorderLayout.CENTER);
		panel_comment.add(panel_commentInput, BorderLayout.SOUTH);
		this.setLayout(null);
		JPanel panel_navigator = new JPanel();
		panel_navigator.setLayout(new GridLayout(1, 3, 1, 1));
		panel_navigator.add(button_prev);
		panel_navigator.add(current_page);
		panel_navigator.add(button_next);
		button_prev.addActionListener(this);
		button_next.addActionListener(this);
		JPanel panel_header = new JPanel();
		panel_header.setLayout(new GridLayout(2, 1, 1, 1));
		panel_header.add(getPanel());
		panel_header.add(panel_navigator);
		panel_header.setBounds(0,0,800,80);
		panel_main.setBounds(0,80,800,120);
		panel_comment.setBounds(0,200,800,250);
		this.add(panel_header);
		this.add(panel_main);
		this.add(panel_comment);                               
    }

	
	/**
     * Set the paint trainers on the panel
     * @return void
     */
	private void paintTrainers(int page){
		for(int i = 0; i < button_trainer.length; i++){
			if((i + page * volume) < trainersList.size()){
				button_trainer[i].setText(trainersList.get(i + page * volume).getName());
			}
		}
	}

	/**
     * The method is the getter of rowsnum
     * @return int the rowsnum of commentarea
     * @seeUser
     */
	private int getRowsNum(){
		return rowsNum;
	}

	/**
     * The method is the setter of rowsnum
     * @return void
     * @seeUser
     */
	private void setRowsNum(int num){
		this.rowsNum = num;
	}

	/**
     * The method is the getter of trainer
     * @return Trainer the trainer currently select
     * @seeUser
     */
	private Trainer getTrainer(){
		return trainer;
	}

	/**
     * The method is the setter of trainer
     * @return void
     * @seeUser
     */
	private void setTrainer(Trainer input){
		this.trainer = input;
		System.out.println(input.getName());
		haveTrainer++;
	}

	/**
     * The method is the setter of comment area
     * @return void
     * @seeUser
     */
	private void setComment(ArrayList<Chat> chatList){
		//System.out.println("set: " + chatList.toString());
		String chatString = "The Chat With " + this.getTrainer().getName() + " :\n";
		int counter = 0;
		for(Chat chat:chatList){
			if(chat.getType() == 1){
				chatString += "from " + GUIController.getUser().getName() + " to " + this.getTrainer().getName()+ ": " + chat.getMessage();
			}else{
				chatString += "from " + this.getTrainer() + " to " + GUIController.getUser().getName() + ": " + chat.getMessage();
			}
			chatString += "\n";
			counter++;
		}
		this.setRowsNum(counter);
		commentArea.setText(chatString);
		commentArea.setLineWrap(true);
		commentArea.setRows(this.getRowsNum());
	}
	/**
     * The method is the Getter of comment area text
     * @return String Text of the comment area
     * @seeUser
     */
	private String getComment() {
		return commentArea.getText();
	}

	/**
     * The method will append new comment to comment area text
     * @return void
     * @seeUser
     */
	private void appendComment(String input) {
		//System.out.println("append : " + input);
		this.setRowsNum(this.getRowsNum() + 1);
		commentArea.setRows(this.getRowsNum());
		commentArea.append("from " + GUIController.getUser().getName() + " to " + this.getTrainer().getName()+ ":  " + input + "\n");
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
			//System.out.println(haveTrainer);
			if(haveTrainer > 1){
				ChatController chatController = new ChatController((Customer) UserController.getUserByUsername(GUIController.getUser().getName()), trainer);
				chatController.Send(UserController.getUserByUsername(GUIController.getUser().getName()), commentInput.getText());
				//System.out.println(commentInput.getText());
				this.appendComment(commentInput.getText());
			}
		}else if(e.getSource()==button_next){
			if((pageNum + 1) * volume < trainersList.size()){
				pageNum++;
				current_page.setText("" + (pageNum + 1));
				paintTrainers(pageNum);
			}
		}else if(e.getSource()==button_prev){
			if(pageNum > 0){
				pageNum--;
				current_page.setText("" + (pageNum + 1));
				paintTrainers(pageNum);
			}
		}else{
			for(int i = 0; i < button_trainer.length; i++){
				if(e.getSource().equals(button_trainer[i])){
					if(i < trainersList.size()){
						setTrainer(trainersList.get(i));
						ChatController chatController = new ChatController((Customer) UserController.getUserByUsername(GUIController.getUser().getName()), trainer);
						this.setComment(chatController.getMessagesList());
					}
				}
			}
		}
    }
}


