package warehouse.client.networking.adminManageShopClient;

public interface AdminManageShopClient
{
  void createShop(String city, String street,String zipCode, String clientId);
  void getAllShops(String clientId);
}
