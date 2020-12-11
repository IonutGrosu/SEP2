package warehouse.persistence.login;

import warehouse.shared.transferObjects.User;

public interface LoginDAO {
    User checkCredentials(String username, String password);
}
