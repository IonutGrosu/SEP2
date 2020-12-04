package warehouse.persistence.manageShops;

public interface ManageShopsDAO
{
  boolean createShop(int id, String city, String street);
  boolean checkIfShopExists(int id, String city, String street);
}
