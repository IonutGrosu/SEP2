package warehouse.shared.networking.adminManageShop;

import warehouse.shared.transferObjects.Shop;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface AdminManageShopClientCallback extends Remote
{
  void successCreateShopResponse(Shop createdShop) throws RemoteException;
  void errorCreateShopResponse() throws RemoteException;
  void allShopsResponse(ArrayList<Shop> allShops) throws RemoteException;
  void deleteShopResponse(String response) throws RemoteException;
}
