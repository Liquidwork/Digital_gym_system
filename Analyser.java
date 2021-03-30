import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Title      : Analyser.java
 * Description: This class will recive the website and the times try to get access to it,
				then use cmd to get access to the website in order to test the ping of the
				website, finally return the string of information recieved from the website.
 * @author      Han Xiao
 * @version     1.2
 */
public class Analyser {
	/** This is the empty constructor of Analyser, since now
	 *  we do need to impel any action for the constructor.
	 */
    public Analyser() {
		
	}
	
	/** This method will test the ping of the website. First, we wee test whether the input website is valid,
	 *  and then, by trying to get access to the website and recieve information from it, we can return the
	 *  string of the information recieved from the website.
	 *  @param input  The website for testing.
	 *  @param selectedData The times user selece for test.
	 *  @return String The information recieved from the website.
	 */
	public String analyse(String input, String selectedData) {
		// Create the variable pingStr to hold the string which using for calling the website.
		String pingStr = "ping -n "+ selectedData + " " + input;
		// Create the bufferedReader br to be null.
        BufferedReader br = null;
		// Create the StringBuilder sb.
		StringBuilder sb= new StringBuilder();
		// Test whether the input website is valid by calling the method of checkUrl().
		if(!checkUrl(input)) {
			// If not valid, then append the WRONG URL to the top of the StringBuilder to inform user.
			sb.append("WRONG URL\n");
		}
		// Calling the website.
        try {
            Process p = Runtime.getRuntime().exec(pingStr);
            br = new BufferedReader(new InputStreamReader(p.getInputStream(),"GBK"));
			// Initialize String line to be null for holding the information read by line.
            String line = null;
			// Keep reading while the information is not end.
            while ((line = br.readLine()) != null) {
				//: Add the new line of information to the StringBuilder.
                sb.append(line+"\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
		// Return the information recieved from the website as String.
		return sb.toString();
    }
	
	/** This method will check whether the input website is valid
	 *  by confirm that the input website is not empty and have
	 *  at least one dot '.' to be a valid website.
	 *  @param utl_s The String of input URL.
	 *  @return boolean Whether the input URL is valid or not.
	 */
	public boolean checkUrl(String url_s) {
		// If the input URL is empty.
		if(url_s.equals("")) {
			// Then return false.
			return false;
		}
		// If the input URL have no dot '.'.
		else if(url_s.indexOf('.') == -1) {
			// Then return false.
			return false;
		}
		// Otherwise, the URL will seems to be valid.
		else {
			// Then return ture.
			return true;
		}
    }
}