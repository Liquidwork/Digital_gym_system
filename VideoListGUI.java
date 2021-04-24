import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel; 
/**
 * A VideoPlayer class which provide video GUI panel
 */
public class VideoListGUI extends RootGUI implements ActionListener{
	private int pageNum = 0;
	private final int volume = 25;
	private JTextField search_field = new JTextField();
	private JComboBox<String> search_comboBox = new JComboBox<>();
	private JButton button_prev = new JButton("prev page");
	private JButton button_next = new JButton("next page");
	private JButton button_search = new JButton("search");
	private JButton button_reset = new JButton("reset");
	private JButton button_upload = new JButton("upload");
	private JLabel current_page = new JLabel("1", JLabel.CENTER);
	private JLabel search_label = new JLabel("Please type keyword and select type of search: ",JLabel.CENTER);
	private JButton[] button_video = new JButton[25];
	private ArrayList<Video> videosList;
	/**
     * Initialize GUI frame then add the CustomerSchedule panel to the frame
     * The method will attach the CustomerSchedule panel to the frame
     * @param frame the frame for display the GUI
     * @return void
     * @seeUser
     */
    public VideoListGUI() {
        Font font = new Font("Dialog",Font.BOLD,16);
		button_prev.setFont(font);
		button_next.setFont(font);
		button_search.setFont(font);
		search_comboBox.addItem("Search Type: Title");
		search_comboBox.addItem("Search Type: Author");
		search_comboBox.addItem("Search Type: ID");
		JPanel panel_main = new JPanel();
		panel_main.setLayout(new GridLayout(5, 5, 3, 3));
		videosList = VideoController.getVideosList();
		for(int i = 0; i < button_video.length ;i++){
			button_video[i] = new JButton(" ");
			button_video[i].addActionListener(this);
			panel_main.add(button_video[i]);
		}
		this.paintVideos(0);
		this.setLayout(null);
		JPanel panel_navigator = new JPanel();
		panel_navigator.setLayout(new GridLayout(1, 3, 1, 1));
		panel_navigator.add(button_prev);
		panel_navigator.add(current_page);
		panel_navigator.add(button_next);
		button_prev.addActionListener(this);
		button_next.addActionListener(this);
		JPanel panel_combo = new JPanel();
		panel_combo.setLayout(new GridLayout(1, 3, 1, 1));	
		panel_combo.add(button_reset);
		panel_combo.add(search_comboBox);

		//Upload Button only for trainer
		if(GUIController.getUser().getClass() == Trainer.class){
			panel_combo.add(button_upload);
			button_upload.addActionListener(this);
		}
		button_reset.addActionListener(this);
		JPanel panel_search = new JPanel();
		panel_search.setLayout(new BorderLayout());
		panel_search.add(search_label, BorderLayout.WEST);
		panel_search.add(search_field, BorderLayout.CENTER);
		panel_search.add(button_search, BorderLayout.EAST);
		panel_search.add(panel_combo, BorderLayout.NORTH);
		button_search.addActionListener(this);
		JPanel panel_header = new JPanel();
		panel_header.setLayout(new GridLayout(3, 1, 1, 1));
		panel_header.add(getPanel());
		panel_header.add(panel_navigator);
		panel_header.add(panel_search);
		panel_header.setBounds(0,0,750,150);
		panel_main.setBounds(0,150,800,300);
		this.add(panel_header);
		this.add(panel_main);                            
    }

	
	/**
     * Set the paint videos on the panel
     * @return void
     */
	private void paintVideos(int page){
		pageNum = page;
		current_page.setText("" + (pageNum + 1));
		//System.out.println(videosList.toString());
		for(int i = 0; i < button_video.length; i++){
			if((i + page * volume) < videosList.size()){
				button_video[i].setText(videosList.get(i + page * volume).getTitle() + " by " 
				+ videosList.get(i + page * volume).getAuthor().getName());
			}else{
				button_video[i].setText("");
			}
		}
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
		if(e.getSource()==button_next){
			//System.out.println(pageNum * volume + " " + videosList.size() + " " + (pageNum * volume > videosList.size()));
			if((pageNum + 1) * volume < videosList.size()){
				pageNum++;
				paintVideos(pageNum);
			}
		}else if(e.getSource()==button_prev){
			if(pageNum > 0){
				pageNum--;
				paintVideos(pageNum);
			}
		}else if(e.getSource()==button_search){
			//System.out.println("SEARCH: " + search_field.getText() + " " + search_comboBox.getSelectedItem());
			try{
				if(search_comboBox.getSelectedItem() == "Search Type: Title"){
					videosList = VideoController.searchVideosByTitle(search_field.getText());
					paintVideos(0);
				}else if(search_comboBox.getSelectedItem() == "Search Type: Author"){
					videosList = VideoController.getVideosByAuthor(UserController.getUserByUsername(search_field.getText()));
					paintVideos(0);
				}else if(search_comboBox.getSelectedItem() == "Search Type: ID"){
					videosList = new ArrayList<>();
					videosList.add(VideoController.getVideoById(Integer.parseInt(search_field.getText())));
					paintVideos(0);
				}
			}catch(Exception exception){
				videosList = new ArrayList<>();
				paintVideos(0);
			}
		}else if(e.getSource()==button_reset){
			videosList = VideoController.getVideosList();
			paintVideos(0);
		}else if(e.getSource()==button_upload){
			GUIController.navigateTo(new VideoUploaderGUI());
		}else{
			for(int i = 0; i < button_video.length; i++){
				if(e.getSource().equals(button_video[i])){
					if(i < videosList.size()){
						VideoPlayerGUI videoPlayerGUI = new VideoPlayerGUI(videosList.get(i));
						GUIController.navigateTo(videoPlayerGUI);
					}
				}
			}
		}
    }
}


