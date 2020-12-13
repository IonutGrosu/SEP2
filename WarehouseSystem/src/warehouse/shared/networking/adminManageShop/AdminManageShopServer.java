package warehouse.shared.networking.adminManageShop;

import warehouse.client.networking.adminManageShopClient.AdminManageShopClientImpl;
import warehouse.shared.networking.ClientCallback;
import warehouse.shared.transferObjects.Shop;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AdminManageShopServer extends Remote
{
  void createShop(String city, String street,String zipCode, String clientId, AdminManageShopClientCallback adminManageShopClientCallback) throws
      RemoteException;
  void getAllShops(String clientId, AdminManageShopClientCallback adminManageShopClientCallback) throws RemoteException;
  void deleteShop(String clientId, Shop shop, AdminManageShopClientCallback adminManageShopClientCallback) throws RemoteException;
}
