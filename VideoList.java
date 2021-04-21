import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class will initialize a list include all videos to
 * choose from, with a searching method.
 */
public class VideoList extends JPanel{
    public JButton searchButton;
    public JButton cancelButton; // cancel searching
    public JTextField searchBox;
    public LinkedList<JButton> searchList;

    /**
     * Initiate a new video list.
     */
    public VideoList(){
        super();
        this.setLayout(null);
        // Initialize title
        JLabel title = new JLabel("Recorded Lectures", JLabel.CENTER);
        title.setBounds(100, 50, 600, 80);
        title.setFont(new Font("Arial", Font.BOLD, 48));
        this.add(title);
        // Initialize search box
        this.searchBox = new JTextField();
        this.searchBox.setBounds(200, 150, 200, 20);
        this.add(searchBox);
        // Initialize search button
        this.searchButton = new JButton("Search");
        this.searchButton.setBounds(400, 150, 80, 20);
        this.searchButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                VideoList.this.search(); // use the search method in outer class
        }});
        this.add(searchButton);
        this.cancelButton = new JButton("Cancel");
        this.cancelButton.setBounds(480, 150, 80, 20);
        // Try lambda expression here
        this.cancelButton.addActionListener(e -> VideoList.this.initializeList()); // use the init method in outer class
        this.add(cancelButton);

        // Initialize the video list at first
        this.initializeList();
    }

    /**
     * Draw the default search list, if there is a list initiated, 
     * overwrite it.
     */
    private void initializeList(){
        // TODO: Finish this method
    }

    /**
     * Overwrite the previous search list with the searching results
     */
    private void search(){
        // TODO: Finish this method
    }




    /**
     * For test, run here to see your frame
     * @param args no usage
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        VideoList videoPanel = new VideoList();
        frame.setTitle("Video Panel Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,500);
        frame.setLocation(400, 100);
        frame.setVisible(true);
        frame.add(videoPanel);
    }
}
