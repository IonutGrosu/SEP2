package warehouse.client.model;

import warehouse.shared.util.PropertyChangeSubject;

public interface WarehouseModel extends PropertyChangeSubject {
    void login(String username, String password);
    void createShop(String city, String street, String zipCode);
    void setUserProperties(String firstName, String lastName, String username,
      String password, String position);
}
