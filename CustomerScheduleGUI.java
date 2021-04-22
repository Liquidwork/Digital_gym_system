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
public class CustomerScheduleGUI extends RootGUI implements ActionListener{
    private JPanel panel = new JPanel();
	private JComboBox<String> MonthBox = new JComboBox<>();
	private JComboBox<String> YearBox = new JComboBox<>();
	
	private JLabel YearLabel = new JLabel("Year:");
	private JLabel MonthLabel = new JLabel("Month:");
	
	private JButton button_ok = new JButton("OK");
	private JButton button_today = new JButton("Today");
	
	
	private Date now_date = new Date();
	
	private int now_year = now_date.getYear() + 1900;
	private int now_month = now_date.getMonth();
	private boolean todayFlag = false;
	
	private JButton[] button_day = new JButton[42];
	private final String[] week = {"SUN","MON","TUE","WEN","THR","FRI","SAT"};
	private JButton[] button_week = new JButton[7];
	private JButton[] button_courses = new JButton[5];
	private String year_int = null;
	private int month_int;
	/**
     * Initialize GUI frame then add the CustomerSchedule panel to the frame
     * The method will attach the CustomerSchedule panel to the frame
     * @param frame the frame for display the GUI
     * @return void
     * @seeUser
     */
    public CustomerScheduleGUI() {
        Font font = new Font("Dialog",Font.BOLD,16);
		YearLabel.setFont(font);
		MonthLabel.setFont(font);
		button_ok.setFont(font);
		button_today.setFont(font);
		for(int i = now_year - 20;i <= now_year + 100;i++){
			YearBox.addItem(i+"");
		}
		YearBox.setSelectedIndex(20);
		
		for(int i = 1;i < 13;i++){
			MonthBox.addItem(i+"");
		}
		MonthBox.setSelectedIndex(now_month);
		
		JPanel panel_ym = new JPanel();
		panel_ym.add(YearLabel);
		panel_ym.add(YearBox);
		panel_ym.add(MonthLabel);
		panel_ym.add(MonthBox);
		panel_ym.add(button_ok);
		panel_ym.add(button_today);
		button_ok.addActionListener(this);
		button_today.addActionListener(this);
		
		JPanel panel_day = new JPanel();
		//7*7
		panel_day.setLayout(new GridLayout(7, 7, 3, 3));
		for(int i = 0; i < 7; i++) {
			button_week[i] = new JButton(" ");
			button_week[i].setText(week[i]);
			button_week[i].setForeground(Color.black);
			panel_day.add(button_week[i]);
		}
		button_week[0].setForeground(Color.red);
		button_week[6].setForeground(Color.red);
		
		for(int i = 0; i < 42;i++){
			button_day[i] = new JButton(" ");
			button_day[i].addActionListener(this);
			panel_day.add(button_day[i]);
		}
		
		this.paintDay();

		JPanel panel_courses = new JPanel();
		//5*1
		panel_courses.setLayout(new GridLayout(5, 1, 3, 3));
		for(int i = 0; i < 5; i++){
			button_courses[i] = new JButton(" ");
			button_courses[i].setText("No More Course");
			button_courses[i].setForeground(Color.black);
			panel_courses.add(button_courses[i]);
		}
		button_courses[0].setText("Please click the date to get information of training at that day.");
		JPanel panel_main = new JPanel();
		panel_main.setLayout(new BorderLayout());
		panel_main.add(panel_day,BorderLayout.SOUTH);
		panel_main.add(panel_ym,BorderLayout.NORTH);
		panel.setLayout(new BorderLayout());
		panel.add(super.getPanel(),BorderLayout.NORTH);
		panel.add(panel_courses,BorderLayout.SOUTH);
		panel.add(panel_main,BorderLayout.CENTER);                               
    }

	/**
     * Set the paint date on the calender
     * @return void
     */
    private void paintDay() {
		if(todayFlag){
			year_int = now_year +"";
			month_int = now_month;
		}else{
			year_int = YearBox.getSelectedItem().toString();
			month_int = MonthBox.getSelectedIndex();		
		}
		int year_sel = Integer.parseInt(year_int) - 1900;
		Date firstDay = new Date(year_sel, month_int, 1);
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(firstDay);
		int days = 0;
		int day_week = 0;
		
		if(month_int == 0||month_int == 2||month_int == 4||month_int == 6
				||month_int == 7||month_int == 9||month_int == 11){
			days = 31;
		}else if(month_int == 3||month_int == 5||month_int == 8||month_int == 10){
			days = 30;
		}else{
			if(cal.isLeapYear(year_sel)){
				days = 29;
			}else{
				days = 28;
			}
		}
		
		day_week = firstDay.getDay();
		int count = 1;
		
		for(int i = day_week;i<day_week+days;count++,i++){
			if(i%7 == 0||(i+1)%7 == 0){
				if((i == day_week+now_date.getDate()-1)&& month_int==now_month && (year_sel == now_year-1900)){
					button_day[i].setForeground(Color.BLUE);
					button_day[i].setText(count+"");
				}else{
					button_day[i].setForeground(Color.RED);
					button_day[i].setText(count+"");
				}
			}else{
				if((i == day_week+now_date.getDate()-1)&& month_int==now_month && (year_sel == now_year-1900)){
					button_day[i].setForeground(Color.BLUE);
					button_day[i].setText(count+"");
				}else{
					button_day[i].setForeground(Color.BLACK);
					button_day[i].setText(count+"");
				}
			}
			
		}
		if(day_week == 0){
			for(int i = days;i<42;i++){
				button_day[i].setText("");
			}
		}else{
			for(int i = 0;i<day_week;i++){
				button_day[i].setText("");
			}
			for(int i=day_week+days;i<42;i++){
				button_day[i].setText("");
			}
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
    @Override
	/**
    * Provide response for different actions of calender
    * The method will response according to different action event source
     * @param e the action event
     * @return void
     * @seeUser
     */
    public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button_ok){
            todayFlag=false;
			this.paintDay();
        }else if(e.getSource()==button_today){
            todayFlag=true;
            YearBox.setSelectedIndex(20);
            MonthBox.setSelectedIndex(now_month);
			this.paintDay();
        }else{
			for(int i = 0; i < button_day.length; i++){
				if(e.getSource().equals(button_day[i])){
					button_courses[0].setText(year_int + " " + month_int + " " + button_day[i].getText());
				}
			}
		}
        
    }
}


