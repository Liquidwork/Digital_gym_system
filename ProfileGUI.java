import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * A CustomerSchedule class which provide Login GUI panel
 */
public class ProfileGUI extends RootGUI implements ActionListener{
    private JLabel emptyLabel = new JLabel("", JLabel.CENTER);
    private JLabel heightLabel = new JLabel("Your Height: ", JLabel.CENTER);
    private JLabel weightLabel = new JLabel("Your Weight: ", JLabel.CENTER);
    private JLabel nameLabel = new JLabel("Hello! ", JLabel.CENTER);
    private JLabel courseLabel = new JLabel("Below is the courses you are going to take ", JLabel.CENTER);
    private JButton[] coursesButton = {new JButton("Course"),new JButton("Course"),new JButton("Course"),new JButton("Course"),new JButton("Course")};

	/**
     * Initialize GUI frame then add the CustomerSchedule panel to the frame
     * The method will attach the CustomerSchedule panel to the frame
     * @param frame the frame for display the GUI
     * @return void
     * @seeUser
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
        for(int i = 0; i < coursesButton.length; i++){
            coursesButton[i].setText("Course" + i);
            panel.add(coursesButton[i]);
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
        
    }
}

