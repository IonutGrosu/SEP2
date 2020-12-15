package warehouse.client.networking.adminManageShopClient;

import warehouse.client.networking.Connection;
import warehouse.shared.networking.RMIServer;
import warehouse.shared.networking.adminManageShop.AdminManageShopClientCallback;
import warehouse.shared.transferObjects.EventType;
import warehouse.shared.transferObjects.Shop;

import java.beans.PropertyChangeListener;
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
    support.firePropertyChange(EventType.ALL_SHOPS_LIST.toString(), null, allShops);
  }

  @Override public void deleteShopResponse(String response)
      throws RemoteException
  {
    support.firePropertyChange(response, null, null);
  }

  @Override
  public void getAllShops(String clientId) {
    try {
      rmiServer.getAdminManageShopServer().getAllShops(clientId, this);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  @Override public void deleteShop(String clientId, Shop shop)
  {
    try
    {
      rmiServer.getAdminManageShopServer().deleteShop(clientId, shop, this);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void addPropertyListener(String eventName,
      PropertyChangeListener listener)
  {
    if(eventName == null)
    {
      support.addPropertyChangeListener(listener);
    } else {
      support.addPropertyChangeListener(eventName, listener);
    }
  }
}
