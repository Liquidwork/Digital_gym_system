import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel; 
/**
 * A LiveTrainingGUI class which provide live GUI panel
 */
public class LiveTrainingGUI extends LeafGUI implements ActionListener{
	private CustomerScheduleGUI parent;
	private JButton button_remove = new JButton("remove");
	private JLabel videoLabel = new JLabel("Live", JLabel.CENTER);
	private JLabel videoName = new JLabel("Live Title", JLabel.CENTER);
	private JLabel videoDesc = new JLabel("Live Description", JLabel.CENTER);
	private JLabel videoAuthor = new JLabel("Live Author", JLabel.CENTER);
	private JLabel videoPath = new JLabel("Live Path", JLabel.CENTER);
	private LiveTraining liveTraining;

	/**
     * Initialize GUI frame then add the LiveTrainingGUI panel to the frame
     * The method will attach the LiveTrainingGUI panel to the frame
     * @param liveTraining the live training to be shown
     */
    public LiveTrainingGUI(LiveTraining liveTraining) {
		this.liveTraining = liveTraining;
        Font font = new Font("Dialog",Font.BOLD,16);
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
		videoName.setText("Live Trainer: " + liveTraining.getTrainer().getName());
		videoDesc.setText("Live Customer: " + liveTraining.getCustomer().getName());
		videoAuthor.setText("Live Time Block: " + liveTraining.getTime());
		videoPath.setText("Live Path: " + liveTraining.getPath());

		JPanel panel_remove = new JPanel();
		panel_remove.setLayout(new GridLayout(2,1,1,1));
		panel_remove.add(getPanel());
		button_remove.setFont(font);
		panel_remove.add(button_remove);
		button_remove.addActionListener(this);
		this.setLayout(new BorderLayout());
		this.add(panel_remove, BorderLayout.NORTH);
		this.add(panel_main, BorderLayout.CENTER);
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
		if(e.getSource() == button_remove){
			// System.out.println(this.liveTraining.getTime());
			if(liveTraining.getCustomer() == GUIController.getUser() || liveTraining.getTrainer() == GUIController.getUser()){
				try{
					LiveTrainingController liveTrainingController = new LiveTrainingController(liveTraining.getDate());
					liveTrainingController.removeTraining(liveTraining);
					GUIController.switchPage(new CustomerScheduleGUI());
				}catch(Exception ex){
					button_remove.setText(ex.getMessage());
				}
			}else{
				button_remove.setText("You can only remove live training for yourself");
			}
		}
    }
}


