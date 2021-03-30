import java.util.*;
import java.text.SimpleDateFormat;
import java.time.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.nio.CharBuffer ;
import java.nio.charset.Charset ;
import java.nio.charset.CharsetDecoder ;
import java.nio.charset.CharsetEncoder ;

/**
 * Title      : GUIGenerator.java
 * Description: This class will create the GUI with the URL and times inputed by user.
				It will contact with Analyser class to collect information about the ping.
 * @author      Han Xiao
 * @version     1.2
 */
public class GUIGenerator {
	// Create the frame of GUI.
	public static JFrame GUIFrame;
	// Create the text area of GUI which can display the information received from the website.
	public JTextArea textArea;
	// Create the text area of GUI which can display the histogram.
	public JLabel histogram1;
	public JLabel histogram2;
	public JLabel histogram3;
	public GUIGenerator(int times) {
		// Window frame
		// Create object of analyser.
		Analyser analyser = new Analyser();
		// Assign a object of JFrame of GUIFrame.
		GUIFrame = new JFrame();
		// Set the title of GUIFrame.
		GUIFrame.setTitle("NetAnalyser V1.0");
		// Set the bounds of GUIFrame.
		GUIFrame.setBounds(600, 300, 800, 300);
		// Set the default close operation.
		GUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Create a new panel which will be assign to the west part of frame.
		JPanel leftPanel = new JPanel();
		// Add the panel to the frame.
		GUIFrame.add(leftPanel, BorderLayout.WEST);
		// Set the layout of the panel to three rows and one column Gridlayout.
		leftPanel.setLayout(new GridLayout(3,1));
		// Set the style of flowLayout.
		FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT,20,100);
		// Create the first laybel.
		JLabel label1 = new JLabel("Enter URL & no. of probes and click on Process");
		// Create two panels to hold textField and other functional components.
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		// Add label and panels to the leftPanel.
		leftPanel.add(label1);
		leftPanel.add(panel1);
		leftPanel.add(panel2);
		// Create the second label.
		JLabel label2 = new JLabel("Test URL");
		//Create the textField to enter Test URL.
		JTextField textField = new JTextField("Enter Test URL");
		// Set the panel1 to be flowLayout and add label and textField to it.
		panel1.setLayout(new FlowLayout());
		panel1.add(label2);
		panel1.add(textField);
		// Create the third label.
		JLabel label3 = new JLabel("No. of probes ");
		// Create the list of data to hold the times and create teh comboBox.
		String[] listData = new String[times];
		for(int i = 0; i < times; i++) {
			listData[i] = String.valueOf(i + 1);
		}
		// Create the comboBox with listData.
        final JComboBox<String> comboBox = new JComboBox<String>(listData);
		// Create the button of process.
		JButton button = new JButton("Process");
		// Set the layout of panel2 to be flowLayout and add components to it.
		panel2.setLayout(new FlowLayout());
		panel2.add(label3);
		panel2.add(comboBox);
		panel2.add(button);
		// Add teh action listener for the button of process.
		button.addActionListener(new ActionListener() {
			/** This method will be activated when the button of process been clicked
			 *  it will call the Analyser and recieve information from the website, then
			 *  assign the proper information to the textArea and histogram.
			 *  @param e  The action which start the action listener.
			 * @Override
			 */
			public void actionPerformed(ActionEvent e) {
				// Collect the data of URL typed by user.
				String input = textField.getText();
				// Collect the data of how many time did user want to test the ping of website.
				String selectedData = (String)comboBox.getSelectedItem();
				// Call analyser with parameters of input and selectedData to collect information which received from the website.
				String ping = analyser.analyse(input, selectedData);
				// Create the String of line which will be used for holding line of data.
				String line;
				// Set these varables which will be used latter.
				int index1 = 0, index2 = 0, max = 0, min = -1, avg = 0, timeOut = 0, countLine = -2;
				// Create the array which are going to hold the value of RTT.
				int[] RTTArray = new int[Integer.parseInt(selectedData)];
				// Create the buffer reader.
				BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(ping.getBytes(Charset.forName("utf8"))), Charset.forName("utf8")));  
				// Create the string buffer.
				StringBuffer strbuf=new StringBuffer();
				try{
					// Keep reading lines until the end.
					while ( (line = br.readLine()) != null ) {
						if(!line.trim().equals("")){
							// Add new line to the variable of line.
							line="<br>"+line;
							// Append line to the strbuf.
							strbuf.append(line+"\r\n");
							// There have two lines without information about RTT, so we skip them by set the countLine initialy to -2.
							if(countLine < 0) {
							}
							// If countLine >= 0 and < selectedData, means we can read the information of RTT.
							else if(countLine < Integer.parseInt(selectedData)) {
								// If there have no "=", then set the corresponding RTT value to -1 mark there have no information, might be timeout.
								if(line.indexOf("=") == -1) {
									RTTArray[countLine] = -1;
									timeOut++;
								}
								else {
									// First find the index of "ms".
									index1 = line.lastIndexOf("ms");
									// Then find the index of '='.
									index2 = line.lastIndexOf('=', index1 - 1);
									// In that case, the substring between index1 and index2 will be the value of RTT.
									String RTT = line.substring(index2 + 1, index1);
									// Then record the value into RTTArray.
									RTTArray[countLine] = Integer.parseInt(RTT);
								}
							}
							// If the val of timeOut is larger than selectedData, means there have no information received.
							else if(timeOut >= Integer.parseInt(selectedData)) {
								// Set min to -1 to mark there have no information then end the loop.
								min = -1;
								break;
							}
							// When the countLine >= selectedData + 4, means we have reach the line where have the information of max RTT and min RTT.
							else if(countLine >= (Integer.parseInt(selectedData) + 4)) {
								// Then we collect the max and min.
								index1 = line.indexOf('=');
								index2 = line.indexOf("ms", index1 + 2);
								String minString = line.substring(index1 + 2, index2);
								min = Integer.parseInt(minString);
								index1 = line.indexOf('=', index2);
								index2 = line.indexOf("ms", index1 + 2);
								String maxString = line.substring(index1 + 2, index2);
								max = Integer.parseInt(maxString);
							}
						}
						// Raise the countLine to keep the loop foward.
						countLine++;
					}
				}
				// If there have an IOException, then alarm the user and set the min to -1 means error.
				catch (IOException ioe) {
				  System.out.println("Errors occured: IOException\n" + ping);
				  min = -1;
				}
				// Create the String of histogramText to hold the data will later been displaied in the histogram.
				String histogramText1 = "";
				String histogramText2 = "";
				String histogramText3 = "";
				// Initialize the int variable for counting the number of star should been print on the histogram.
				int count1 = 0, count2 = 0, count3 = 0;
				// If min = -1, means something wrong, then histogram will alarm user. In that case, user needn't to restart the prograge, they can just simply check their URL and internet. 
				if(min == -1) {
					histogramText1 = "No reply for ping, please check the URL and internet connection.\n";
				}
				// If there have too small difference between max and min, histogram will display with only one bar.
				else if((max - min) < 3) {
					histogramText1 = histogramText1 + String.valueOf(min) + "ms <= RTT <= " + String.valueOf(max) + "ms :";
					for(int j = 0; j < Integer.parseInt(selectedData); j++) {
						histogramText1 = histogramText1 + " * ";
					}
				}
				// Otherwise, we are going to count the stars to display the histogram.
				else {
					// Counting for the stars should been displaied.
					for(int k = 0; k < Integer.parseInt(selectedData); k++) {
						if(RTTArray[k] == -1) {
						}
						else if(RTTArray[k] <= ((int)(min + (max - min) * 0.33))) {
							count1 += 1;
						}
						else if(RTTArray[k] <= ((int)(min + (max - min) * 0.66))) {
							count2 += 1;
						}
						else {
							count3 += 1;
						}
					}
					// Start to display the first bar.
					histogramText1 = histogramText1 + String.valueOf(min) + "ms <= RTT <= " + String.valueOf((int)(min + (max - min) * 0.33)) + "ms :";
					for(int l = 0; l < count1; l++) {
						histogramText1 = histogramText1 + " * ";
					}
					// The second bar.
					histogramText2 = histogramText2 + String.valueOf((int)(min + (max - min) * 0.33)) + "ms <= RTT <= " + String.valueOf((int)(min + (max - min) * 0.66)) + "ms :";
					for(int m = 0; m < count2; m++) {
						histogramText2 = histogramText2 + " * ";
					}
					// The third bar.
					histogramText3 = histogramText3 + String.valueOf((int)(min + (max - min) * 0.66)) + "ms <= RTT <= " + String.valueOf(max) + "ms :";
					for(int n = 0; n < count3; n++) {
						histogramText3 = histogramText3 + " * ";
					}
				}
				// Create the String of histogramFile by replace all the '.' of the input URL to '-'.
				String histogramFile = input.replace('.', '-');
				// Create new date and collect the localTime.
				Date day=new Date();    
				SimpleDateFormat localTime = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss"); 
				// If min is -1, means there have something go wrong, then directly alarm user in the file.
				if(min == -1) {
					histogramFile = histogramFile + "-" + localTime.format(day) 
						+ ".txt\n\nNo reply for ping, please check the URL and internet connection.\n";
				}
				// If there only have one access to the website, then directly display that access to the file.
				else if(Integer.parseInt(selectedData) == 1){
					System.out.println("Only One: " + selectedData);
					histogramFile = histogramFile + "-" + localTime.format(day) + ".txt\n\nRT(ms) histogram\n"
						+String.valueOf(min) + " : " + String.valueOf(1) + "\n";
				}
				// Otherwise, write the result of testing into the file with three rows.
				else {
					histogramFile = histogramFile + "-" + localTime.format(day) + ".txt\n\nRT(ms) histogram\n"
						+String.valueOf(min) + "-" + String.valueOf((int)(min + (max - min) * 0.33)) + " : " + String.valueOf(count1) + "\n"
						+String.valueOf((int)(min + (max - min) * 0.33)) + "-" + String.valueOf((int)(min + (max - min) * 0.66)) + " : " + String.valueOf(count2) + "\n"
						+String.valueOf((int)(min + (max - min) * 0.66)) + "-" + String.valueOf((int)(max)) + " : " + String.valueOf(count3) + "\n";
				}
				// Write the file.
				try {
					String filename = input.replace('.', '-') + "-" + localTime.format(day) + ".txt";
					FileWriter writer = new FileWriter(filename);
					writer.write(histogramFile);
					writer.close(); 
				}
				catch (IOException ioe) {
				  System.out.println("Errors occured: IOException\n" + ping);
				  min = -1;
				}
				// Set the text of textArea and histogram.
				textArea.setText(ping);
				histogram1.setText(histogramText1);
				histogram2.setText(histogramText2);
				histogram3.setText(histogramText3);
			}
	});
		// Create the scroll pane for the text area.
		JScrollPane scrollPane = new JScrollPane();
		// Add the scrollPane to the GUIFrame.
		GUIFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		// Crete the textArea to be not Editable.
		textArea = new JTextArea("Your output will appear here");
		textArea.setEditable(false); 
		// Assign the textArea to the scrollPane.
		scrollPane.setViewportView(textArea);
		// Create the histogramPanel.
		JPanel histogramPanel = new JPanel(new GridLayout(3, 1));
		// Assign histogramPanel to the east of the GUIFrame.
		GUIFrame.getContentPane().add(histogramPanel, BorderLayout.EAST);
		// Create the histogram and add it to the histogramPanel.
		histogram1 = new JLabel("");
		histogram2 = new JLabel("");
		histogram3 = new JLabel("");
		histogramPanel.add(histogram1);
		histogramPanel.add(histogram2);
		histogramPanel.add(histogram3);
		//Had better at the end.
		GUIFrame.setDefaultLookAndFeelDecorated(true);
		GUIFrame.setVisible(true);
	}
}