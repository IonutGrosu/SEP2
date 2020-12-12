package warehouse.client.networking.adminManageShopClient;

import warehouse.client.networking.Connection;
import warehouse.shared.networking.RMIServer;
import warehouse.shared.networking.adminManageShop.AdminManageShopClientCallback;
import warehouse.shared.transferObjects.EventType;
import warehouse.shared.transferObjects.Shop;

import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class AdminManageShopClientImpl implements AdminManageShopClient,
    AdminManageShopClientCallback
{
  private RMIServer rmiServer;
  private PropertyChangeSupport support;

  public AdminManageShopClientImpl()
  {
    try
    {
      UnicastRemoteObject.exportObject(this, 0);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    support = new PropertyChangeSupport(this);
    rmiServer = Connection.getRmiServer();
  }

  @Override
  public void createShop(String city, String street,String zipCode, String clientId) {
    try {
      rmiServer.getAdminManageShopServer().createShop(city, street, zipCode, clientId ,this);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void successCreateShopResponse(Shop shop) throws RemoteException {
    support.firePropertyChange(EventType.SUCCESSFUL_SHOP_CREATION.toString(), null, shop);
  }

  @Override
  public void errorCreateShopResponse() throws RemoteException {
    support.firePropertyChange(EventType.UNSUCCESSFUL_SHOP_CREATION.toString(), null, null);
  }

  @Override public void allShopsResponse(ArrayList<Shop> allShops)
      throws RemoteException
  {
    System.out.println("allShops list got back to AdminManageShopClientImpl.java");
    support.firePropertyChange(EventType.ALL_SHOPS_LIST.toString(), null, allShops);
  }

  @Override
  public void getAllShops(String clientId) {
    try {
      rmiServer.getAdminManageShopServer().getAllShops(clientId, this);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }
}
