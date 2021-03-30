/**
 * Title      : NetAnalyser.java
 * Description: This is a class for net analysing by trying contact with a website and display 
 * 				the ping condition to the user by both histogram and the 
 *				raw information returned by the website.
 * @author Han Xiao
 * @version 1.1
 */
public class NetAnalyser {
	/**
	  * Main will check the input of the user to make sure that
	  * the input is between 10 to 20. 
	  * Then, by create a instance of GUIGenerator to call the
	  * GUI and display the information to user.
	  * @param args  Between 10 to 20, determain the times trying to get access to the website.
	  */
	public static void main(String args[]) throws inputException {
		// Declear the variable times for storge the input value with int type.
		int times = 0;
		// Use try to check whether the input of user is valid.
		try{
			// Parse the input into int and store in the times variable.
			times = Integer.parseInt(args[0]);
			// Check whether the times is between 10 to 20.
			if(times > 20 || times < 10) {
				// If input is invalid, throw the inputException.
				throw new inputException();
			}
		}
		// If we catch the NumberFormatException, means that there have some problem with the number formation of the input.
		catch(NumberFormatException nfe) {
			// Alert user with the exception of number format.
			System.out.println("Meet number format exception, please type a number between 10 to 20, the input will be set as 10.");
			// Set times to 10.
			times = 10;
		}
		// If we catch the inputException, means number input is out of the scope of 10 to 20.
		catch(inputException ie) {
			// Alert user with the exception of input out of scope;
			System.out.println("Out of scope, please type a number between 10 to 20, the input will be set as 10.");
			// Set times to 10.
			times = 10;
		}
		// After check the input, we call the GUIGenerator and sent the input to it to create the GUI.
		GUIGenerator Ipa = new GUIGenerator(times);
	}
}