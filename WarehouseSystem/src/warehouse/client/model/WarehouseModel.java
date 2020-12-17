package warehouse.client.model;

import warehouse.shared.transferObjects.User;
import warehouse.shared.transferObjects.Shop;
import warehouse.shared.util.PropertyChangeSubject;

public interface WarehouseModel extends PropertyChangeSubject {
    User login(String username, String password);
    void createShop(String city, String street, String zipCode);
    void setUserProperties(String firstName, String lastName, String username,
      String password, String position);
    void getAllShops();
    void getAllUsers();
    void deleteShop(Shop shop);
}
