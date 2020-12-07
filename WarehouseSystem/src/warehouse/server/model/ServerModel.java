package warehouse.server.model;

import warehouse.shared.util.PropertyChangeSubject;

public interface ServerModel extends PropertyChangeSubject {
    boolean checkCredentials(String username, String password);
    void newUser(String firstName, String lastName, String username, String password, String position);
    void createShop(String city, String street, String clientId);
}
