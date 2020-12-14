package warehouse.client.networking.adminManageShopClient;

import warehouse.shared.transferObjects.Shop;
import warehouse.shared.util.PropertyChangeSubject;

public interface AdminManageShopClient extends PropertyChangeSubject
{
  void createShop(String city, String street,String zipCode, String clientId);
  void getAllShops(String clientId);
  void deleteShop(String clientId, Shop shop);
}
