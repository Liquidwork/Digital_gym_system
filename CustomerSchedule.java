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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CustomerSchedule extends JFrame {
	private static final long serialVersionUID = 4360298097575552707L;
	private JFrame jFrame;
    private JPanel panel = new JPanel();
    //月份和年份下拉 列表框
	private JComboBox<String> MonthBox = new JComboBox<>();
	private JComboBox<String> YearBox = new JComboBox<>();
	
	//年份月份标签
	private JLabel YearLabel = new JLabel("年份：");
	private JLabel MonthLabel = new JLabel("月份：");
	
	//确定和今天按钮
	private JButton button_ok = new JButton("查看");
	private JButton button_today = new JButton("今天");
	
	
	//获取今天的日期、年份和月份
	private Date now_date = new Date();
	
	private int now_year = now_date.getYear() + 1900;
	private int now_month = now_date.getMonth();
	private boolean todayFlag = false;
	
	//用一组按钮显示日期，一共7行7列。第一行是星期
	private JButton[] button_day = new JButton[42];
	private final String[] week = {"SUN","MON","TUE","WEN","THR","FRI","SAT"};
	private JButton[] button_week = new JButton[7];
	private String year_int = null;
	private int month_int;

    public CustomerSchedule(JFrame frame) {
        //Set the position and size of GUI window
        jFrame = frame;
        jFrame.setSize(800,500);
        jFrame.setLocationRelativeTo(null);                    
        jFrame.add(panel);                           
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        /* placeComponents(panel);    */ 
        placeCalender(panel);                       
        jFrame.setVisible(true);                     
    }

    //初始化日历
	private void placeCalender(JPanel panel) {
		Font font = new Font("Dialog",Font.BOLD,16);
		YearLabel.setFont(font);
		MonthLabel.setFont(font);
		button_ok.setFont(font);
		button_today.setFont(font);
		//过去20年--未来20年
		for(int i = now_year - 20;i <= now_year + 100;i++){
			YearBox.addItem(i+"");
		}
		YearBox.setSelectedIndex(20);
		
		for(int i = 1;i <= 13;i++){
			MonthBox.addItem(i+"");
		}
		MonthBox.setSelectedIndex(now_month);
		
		//放置下拉列表框和控制按钮的面板
		JPanel panel_ym = new JPanel();
		panel_ym.add(YearLabel);
		panel_ym.add(YearBox);
		panel_ym.add(MonthLabel);
		panel_ym.add(MonthBox);
		panel_ym.add(button_ok);
		panel_ym.add(button_today);
		CalenderMonitor calenderMonitor = new CalenderMonitor();
		//为两个按钮添加时间监听器
		button_ok.addActionListener(calenderMonitor);
		button_today.addActionListener(calenderMonitor);
		
		
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
			panel_day.add(button_day[i]);
		}
		
		this.paintDay();//显示当前日期
		JPanel panel_main = new JPanel();
		panel_main.setLayout(new BorderLayout());
		panel_main.add(panel_day,BorderLayout.SOUTH);
		panel_main.add(panel_ym,BorderLayout.NORTH);
		panel.add(panel_main);
	}

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

    class CalenderMonitor implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==button_ok){
                todayFlag=false;
                /* this.paintDay(); */
            }else if(e.getSource()==button_today){
                todayFlag=true;
                YearBox.setSelectedIndex(20);
                MonthBox.setSelectedIndex(now_month);
                /* this.paintDay(); */
            }
            
        }
    }
}


