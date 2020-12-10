package warehouse.shared.networking.adminManageShop;

import warehouse.client.networking.adminManageShopClient.AdminManageShopClientImpl;
import warehouse.shared.networking.ClientCallback;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AdminManageShopServer extends Remote
{
  void createShop(String city, String street,String zipCode, String clientId, AdminManageShopClientCallback adminManageShopClientCallback) throws
      RemoteException;
  void getAllShops(String clientId, AdminManageShopClientCallback adminManageShopClientCallback) throws RemoteException;
}
