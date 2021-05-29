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
    private JLabel BMILabel = new JLabel("Your BMI: ", JLabel.CENTER);
    private JLabel BalanceLabel = new JLabel("Your Balance: ", JLabel.CENTER);
    private JLabel nameLabel = new JLabel("Hello! ", JLabel.CENTER);
    private JLabel courseLabel = new JLabel("Below is the courses you are going to take ", JLabel.CENTER);
    private JButton button_BMI = new JButton("Set Your BMI");
    private JButton button_balance = new JButton("Deposit 100$ to balance");
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
        Font smallFont = new Font("Dialog",Font.BOLD,18);
		JPanel panel_main = new JPanel();
		panel_main.setLayout(new BorderLayout());
        JPanel panel_user = new JPanel();
        panel_user.setLayout(new GridLayout(2, 1, 1, 1));
        panel_main.add(panel_user, BorderLayout.CENTER);
        nameLabel.setFont(font);
        panel_user.add(nameLabel);
        if(GUIController.getUser().getClass() == Admin.class){
            StatisticController statisticController = new StatisticController();
            JPanel panel_statistic = new JPanel();
            panel_statistic.setLayout(new GridLayout(5, 2, 1, 1));
            panel_statistic.add(new JLabel("As a Admin, you can see following statistics!", JLabel.CENTER));
            panel_statistic.add(new JLabel("userCount: " + statisticController.getUserCount(), JLabel.CENTER));
            panel_statistic.add(new JLabel("loginCount: " + statisticController.getLoginCount(), JLabel.CENTER));
            panel_statistic.add(new JLabel("chatCount: " + statisticController.getChatCount(), JLabel.CENTER));
            panel_statistic.add(new JLabel("chatMessageCount: " + statisticController.getChatMessageCount(), JLabel.CENTER));
            panel_statistic.add(new JLabel("totalCourseCount: " + statisticController.getTotalCourseCount(), JLabel.CENTER));
            panel_statistic.add(new JLabel("upComingCourseCount: " + statisticController.getUpcomingCourseCount(), JLabel.CENTER));
            panel_statistic.add(new JLabel("videoCount: " + statisticController.getVideoCount(), JLabel.CENTER));
            panel_statistic.add(new JLabel("videoCommentCount: " + statisticController.getVideoCommentCount(), JLabel.CENTER));
            panel_statistic.add(new JLabel("videoViewCount: " + statisticController.getVideoViewCount(), JLabel.CENTER));
            panel_user.add(panel_statistic);
        }
        setNameLabel(GUIController.getUser().getName());
        if(GUIController.getUser().getClass() == Customer.class){
            JPanel panel_info = new JPanel();
            panel_info.setLayout(new GridLayout(2, 3, 1, 1));
            panel_info.add(heightLabel);
            panel_info.add(weightLabel);
            panel_info.add(BMILabel);
            panel_info.add(button_BMI);
            panel_info.add(BalanceLabel);
            panel_info.add(button_balance);
            heightLabel.setFont(smallFont);
            weightLabel.setFont(smallFont);
            BMILabel.setFont(smallFont);
            BalanceLabel.setFont(smallFont);
            BalanceLabel.setText("Your Balance: " + CashController.getCash((Customer) GUIController.getUser()));
            if(BMIController.getBmiByUser((GUIController.getUser())) != null){
                java.text.DecimalFormat df = new java.text.DecimalFormat("#.00"); 
                BMILabel.setText("Your BMI: " + df.format(BMIController.getBmiByUser((GUIController.getUser())).getBMI()));
                heightLabel.setText("Your Height: " + df.format(BMIController.getBmiByUser((GUIController.getUser())).getHeight()));
                weightLabel.setText("Your Weight: " + df.format(BMIController.getBmiByUser((GUIController.getUser())).getWeight()));
            }else{
                BMILabel.setText("Please set your BMI.");
                heightLabel.setText("Please set your height.");
                weightLabel.setText("Please set your weight.");
            }
    
            button_BMI.addActionListener(this);
            button_balance.addActionListener(this);
            panel_main.add(panel_info, BorderLayout.SOUTH);
        }
        setNameLabel(GUIController.getUser().getName());
		this.setLayout(new BorderLayout());
		this.add(getPanel(),BorderLayout.NORTH);
		this.add(panel_main,BorderLayout.CENTER);
        if(GUIController.getUser().getClass() == Trainer.class || GUIController.getUser().getClass() == Customer.class){
            JPanel panel_footer = new JPanel();
            panel_footer.setLayout(new BorderLayout());
            JPanel panel_courses = new JPanel();
            panel_courses.setLayout(new GridLayout(1, 4, 1, 1));
            panel_footer.add(courseLabel, BorderLayout.NORTH);
            panel_footer.add(panel_courses, BorderLayout.CENTER);
            setCourses(panel_courses);
            this.add(panel_footer,BorderLayout.SOUTH);      
        }           
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
                button_courses[Courses.get(k).getTime() - 1].setText(Courses.get(k).getTrainer().getName() + " in " + Courses.get(k).getTime());
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
        if(e.getSource().equals(button_BMI)){
            GUIController.navigateTo(new BMIGUI());
        }else if(e.getSource().equals(button_balance)){
            CashController.addCash((Customer) GUIController.getUser(), 100.0);
            BalanceLabel.setText("Your Balance: " + CashController.getCash((Customer) GUIController.getUser()));
        }else{
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
}


