package DocumentManagementSystem.Datenbank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class UserDatabaseTest {

    @Test
    public void testCheckForExistingUsersTrue(){
        assertTrue(UserDatabase.checkForExistingUsers("admin"));
    }

    @Test
    public void testCheckForExistingUsersFalse(){
        assertFalse(UserDatabase.checkForExistingUsers("Max"));
    }
}
