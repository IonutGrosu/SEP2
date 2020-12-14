package warehouse.persistence.login;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import warehouse.persistence.manageUser.ManageUserDAO;
import warehouse.persistence.manageUser.ManageUserDAOImpl;
import warehouse.shared.transferObjects.User;

import static org.junit.jupiter.api.Assertions.*;

class LoginDAOImplTest {

    LoginDAO loginDAO = LoginDAOImpl.getInstance();

    @Test
    public void testNullCredentials() {
        User userLoggedIn =  loginDAO.checkCredentials(null, null);
        assertTrue(userLoggedIn == null);
    }

    @Test
    public void testEmptyCredentials() {
        User userLoggedIn = loginDAO.checkCredentials("", "");
        assertTrue(userLoggedIn == null);
    }

    @Test
    public void testExistingCredentials() {
        ManageUserDAO userDAO = ManageUserDAOImpl.getInstance();
        int id = userDAO.createUser("unit", "test", "test2", "test2", "ADMIN");
        User testUser = new User(id, "unit", "test", "ADMIN");
        User userLoggedIn = loginDAO.checkCredentials("test2", "test2");
        System.out.println(testUser);
        System.out.println(userLoggedIn);
        assertTrue(testUser.equalsWithoudId(userLoggedIn));
    }
}