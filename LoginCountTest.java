import static org.junit.jupiter.api.Assertions.*;

import exceptions.NoMemberException;
import exceptions.PasswordException;
import org.junit.jupiter.api.Test;

public class LoginCountTest {
    @Test
    public void LoginTest() throws PasswordException, NoMemberException {
        User user1 = UserController.getUserByUsername("winter");
        int count1 = LoginController.getLoginCount(user1);
        User user2 = LoginController.login("winter", "14530529");
        int count2 = LoginController.getLoginCount(user1);

        assertEquals(user1, user2);
        assertEquals(count1 + 1, count2);

    }
}
