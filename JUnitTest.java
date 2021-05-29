import static org.junit.jupiter.api.Assertions.*;

import exceptions.NoMemberException;
import exceptions.PasswordException;
import org.junit.jupiter.api.Test;

public class JUnitTest {
    @Test
    public void LoginTest() throws PasswordException, NoMemberException {
        User user1 = UserController.getUserByUsername("winter");
        int count1 = LoginController.getLoginCount(user1);
        User user2 = LoginController.login("winter", "14530529");
        int count2 = LoginController.getLoginCount(user1);

        assertEquals(user1, user2); // Is the user login correctly?
        assertEquals(count1 + 1, count2); // Has the login count add 1?
    }

    @Test
    public void BMITest(){
        User user1 = UserController.getUserByUsername("luca");
        // Set a new BMI
        BMIController.addBMI(user1, 178.5, 75.3);

        // Get it by getter
        BMI bmi = BMIController.getBmiByUser(user1);
        assertEquals(bmi.getHeight(), 178.5);
        assertEquals(bmi.getWeight(), 75.3);
        assertEquals(bmi.getUser(), user1);
    }
}
