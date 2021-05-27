import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
/**
 * A CustomerSchedule class which provide CustomerSchedule GUI panel
 */
public class CustomerScheduleGUI extends RootGUI implements ActionListener{
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
	private JTextArea coursesArea = new JTextArea("Please click the date to get information of training at that day.");
	private JButton button_add = new JButton("Appoint new Live Training");
	private ArrayList<LiveTraining> Courses = new ArrayList<>();
	private String year_int = null;
	private int month_int;
	private Date date = new Date();
	/**
     * Initialize GUI frame then add the CustomerSchedule panel to the frame
     * The method will attach the CustomerSchedule panel to the frame
     * @return void
     *
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
		
		this.paintDay(-1);

		JPanel panel_courses = new JPanel();
		//5*1
		if(GUIController.getUser().getClass() == Admin.class){
			panel_courses.setLayout(new GridLayout(1, 1, 3, 3));
			coursesArea.setEditable(false);
			coursesArea.setLineWrap(true);
			JScrollPane jsp=new JScrollPane(coursesArea);
			Dimension size=coursesArea.getPreferredSize();
			jsp.setBounds(110,90,size.width,size.height);
			panel_courses.add(jsp);
		}else{
			panel_courses.setLayout(new GridLayout(6, 1, 3, 3));
			if(GUIController.getUser().getClass() == Customer.class){
				panel_courses.add(button_add);
				button_add.addActionListener(this);
			}
			for(int i = 0; i < 5; i++){
				button_courses[i] = new JButton(" ");
				button_courses[i].setText("No Course");
				button_courses[i].setForeground(Color.black);
				panel_courses.add(button_courses[i]);
				button_courses[i].addActionListener(this);
			}
			button_courses[0].setText("Please click the date to get information of training at that day.");
		}
		JPanel panel_main = new JPanel();
		panel_main.setLayout(new BorderLayout());
		panel_main.add(panel_day,BorderLayout.SOUTH);
		panel_main.add(panel_ym,BorderLayout.NORTH);
		this.setLayout(null);
		getPanel().setBounds(0,0,800,80);
		panel_main.setBounds(0,80,800,250);
		panel_courses.setBounds(0,200,800,120);
		this.add(getPanel());
		this.add(panel_main);
		this.add(panel_courses);                                
    }

	/**
     * Set the paint date on the calender
     * @return void
     */
    private void paintDay(int selected) {
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
			if (button_day[i].getText().equals("") || year_int.length() < 2){
				break;
			}else{
				date = new Date(Integer.parseInt(year_int) - 1900, month_int, Integer.parseInt(button_day[i].getText()));
				LiveTrainingController liveTrainingController = new LiveTrainingController(date);
				if(liveTrainingController.getListByUser(GUIController.getUser()).size() > 0){
					if(button_day[i].getForeground() != Color.BLUE){
						button_day[i].setForeground(Color.ORANGE);
						button_day[i].setText(count+"");
					}
				}
			}
			
		}
		if(selected > 0){
			button_day[selected].setForeground(Color.PINK);
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

    @Override
	/**
    * Provide response for different actions of calender
    * The method will response according to different action event source
     * @param e the action event
     * @return void
     *
     */
    public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button_ok){
            todayFlag=false;
			this.paintDay(-1);
        }else if(e.getSource()==button_today){
            todayFlag=true;
            YearBox.setSelectedIndex(20);
            MonthBox.setSelectedIndex(now_month);
			this.paintDay(-1);
        }else if(e.getSource()==button_add){
			if(CashController.getCash((Customer) GUIController.getUser()) > 100){
				GUIController.navigateTo(new AppointLiveTrainingGUI(date));
			}else{
				button_add.setText("Appoint a live course need 100$, you only have " + CashController.getCash((Customer) GUIController.getUser()) + "$");
			}
        }else{
			for(int i = 0; i < button_courses.length; i++){
				if(e.getSource().equals(button_courses[i])){
					for(int k = 0; k < Courses.size(); k++){
						if(i == Courses.get(k).getTime() - 1){
							GUIController.navigateTo(new LiveTrainingGUI(Courses.get(k)));
						}
					}
				}
			}
			for(int i = 0; i < button_day.length; i++){
				if(e.getSource().equals(button_day[i])){
					if (button_day[i].getText().equals("")) break;
					paintDay(i);
					date = new Date(Integer.parseInt(year_int) - 1900, month_int, Integer.parseInt(button_day[i].getText()));
					LiveTrainingController liveTrainingController = new LiveTrainingController(date);
					Courses = liveTrainingController.getListByUser(GUIController.getUser());
					if(GUIController.getUser().getClass() == Admin.class){
						Courses = liveTrainingController.getTrainingList();
						String displayed = "";
						for(LiveTraining course : Courses){
							displayed += course.toString() + "\n";
						}
						coursesArea.setText(displayed);
					}else{
						Courses = liveTrainingController.getListByUser(GUIController.getUser());
						for(int j = 0; j < button_courses.length; j++){
							button_courses[j].setText("No course");
						}
						for(int k = 0; k < Courses.size(); k++){
							button_courses[Courses.get(k).getTime() - 1].setText(Courses.get(k).getCustomer().getName() +  " with " + Courses.get(k).getTrainer().getName() + " at time block " + Courses.get(k).getTime());
						}
					}
				}
			}
		}
        
    }
}


