package warehouse.server.model;

import warehouse.shared.transferObjects.Shop;
import warehouse.shared.transferObjects.User;
import warehouse.shared.util.PropertyChangeSubject;

public interface ServerModel extends PropertyChangeSubject {
    User checkCredentials(String username, String password);
    void newUser(String firstName, String lastName, String username, String password, String position);
    void createShop(String city, String street, String zipCode, String clientId);
    void getAllShops(String clientId);
    void getAllUsers(String clientUsernameId);
    void deleteShop(String clientId, Shop shop);
}
