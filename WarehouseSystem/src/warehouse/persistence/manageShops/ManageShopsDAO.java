package warehouse.persistence.manageShops;

public interface ManageShopsDAO
{
  boolean createShop(int id, String city, String street);
  boolean checkIfShopExists(String city, String street);
}
