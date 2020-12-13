package warehouse.client.networking.adminManageShopClient;

import warehouse.shared.transferObjects.Shop;

public interface AdminManageShopClient
{
  void createShop(String city, String street,String zipCode, String clientId);
  void getAllShops(String clientId);
  void deleteShop(String clientId, Shop shop);
}
