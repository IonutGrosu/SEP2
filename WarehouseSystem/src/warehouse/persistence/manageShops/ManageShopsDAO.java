package warehouse.persistence.manageShops;

import warehouse.shared.transferObjects.Shop;

import java.util.ArrayList;

public interface ManageShopsDAO
{
  int createShop(String city, String street, String zipCode);
  boolean checkIfShopExists(String city, String street);
  ArrayList<Shop> getAllShops();
  void deleteShop(Shop shop);
}
