import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
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
    private JButton[] button_courses = {new JButton("Course"),new JButton("Course"),new JButton("Course"),new JButton("Course"),new JButton("Course")};
    private ArrayList<LiveTraining> Courses = new ArrayList<>();
    Date date = new Date();
    LiveTrainingController liveTrainingController = new LiveTrainingController(date);
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
        Courses = liveTrainingController.getListByUser(GUIController.getUser());
        for(int i = 0; i < button_courses.length; i++){
            for(int j = 0; j < button_courses.length; j++){
                button_courses[j].setText("No course");
            }
            for(int k = 0; k < Courses.size(); k++){
                button_courses[Courses.get(k).getTime() - 1].setText(Courses.get(k).getCustomer().getName() +  " with " + Courses.get(k).getTrainer().getName() + " at time block " + Courses.get(k).getTime());
            }
            panel.add(button_courses[i]);
            button_courses[i].addActionListener(this);
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
        for(int i = 0; i < button_courses.length; i++){
            if(e.getSource().equals(button_courses[i])){
                for(int j = 0; j < Courses.size(); j++){
                    if(i == Courses.get(j).getTime() - 1){
                        GUIController.navigateTo(new LiveTrainingGUI(Courses.get(j)));
                    }
                }
            }
        }
    }
}


