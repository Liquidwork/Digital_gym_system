import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * A ProfileGUI class which provide Profile GUI panel
 */
public class ProfileGUI extends RootGUI implements ActionListener{
    private JLabel heightLabel = new JLabel("Your Height: ", JLabel.CENTER);
    private JLabel weightLabel = new JLabel("Your Weight: ", JLabel.CENTER);
    private JLabel nameLabel = new JLabel("Hello! ", JLabel.CENTER);
    private JLabel courseLabel = new JLabel("Below is the courses you are going to take ", JLabel.CENTER);
    private JButton[] coursesButton = {new JButton("Course"),new JButton("Course"),new JButton("Course"),new JButton("Course"),new JButton("Course")};

	/**
     * Initialize GUI frame then add the ProfileGUI panel to the frame
     * The method will attach the ProfileGUI panel to the frame
     * @return void
     *
     */
    public ProfileGUI() {
        Font font = new Font("Dialog",Font.BOLD,36);
		JPanel panel_main = new JPanel();
		panel_main.setLayout(new BorderLayout());
        JPanel panel_info = new JPanel();
        panel_info.setLayout(new GridLayout(1, 2, 1, 1));
        panel_main.add(nameLabel, BorderLayout.CENTER);
        panel_main.add(panel_info, BorderLayout.SOUTH);
        panel_info.add(heightLabel);
        panel_info.add(weightLabel);
        nameLabel.setFont(font);
        heightLabel.setFont(font);
        weightLabel.setFont(font);
        setNameLabel(GUIController.getUser().getName());
        JPanel panel_footer = new JPanel();
        panel_footer.setLayout(new BorderLayout());
        JPanel panel_courses = new JPanel();
		panel_courses.setLayout(new GridLayout(1, 4, 1, 1));
        panel_footer.add(courseLabel, BorderLayout.NORTH);
        panel_footer.add(panel_courses, BorderLayout.CENTER);
        setCourses(panel_courses);
        setNameLabel(GUIController.getUser().getName());
		this.setLayout(new BorderLayout());
		this.add(getPanel(),BorderLayout.NORTH);
		this.add(panel_main,BorderLayout.CENTER);
        this.add(panel_footer,BorderLayout.SOUTH);                 
    }

    private void setNameLabel(String input){
        nameLabel.setText("Hello! " + input);
    }

    //Set the course which are going to be displayed
    private void setCourses(JPanel panel){
        Date date = new Date();
        LiveTrainingController liveTrainingController = new LiveTrainingController(date);
        for(int i = 0; i < coursesButton.length; i++){
            if(i < liveTrainingController.getListByUser(GUIController.getUser()).size()){
                coursesButton[i].setText(liveTrainingController.getListByUser(GUIController.getUser()).get(i).getDate().toString());
            }else{
                coursesButton[i].setText("No more courses");
            }
            panel.add(coursesButton[i]);
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
        
    }
}


