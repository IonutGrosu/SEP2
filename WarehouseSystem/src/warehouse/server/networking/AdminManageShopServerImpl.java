package warehouse.server.networking;

import warehouse.client.networking.adminManageShopClient.AdminManageShopClientImpl;
import warehouse.server.model.ServerModel;
import warehouse.shared.networking.ClientCallback;
import warehouse.shared.networking.adminManageShop.AdminManageShopClientCallback;
import warehouse.shared.networking.adminManageShop.AdminManageShopServer;
import warehouse.shared.transferObjects.EventType;
import warehouse.shared.transferObjects.Shop;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdminManageShopServerImpl implements AdminManageShopServer
{
  private ServerModel serverModel;
  private PropertyChangeSupport support;
  private Map<String, AdminManageShopClientCallback> hashMap = new HashMap<>();

  public AdminManageShopServerImpl(ServerModel serverModel)
  {
    try
    {
      UnicastRemoteObject.exportObject(this, 0);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    this.serverModel = serverModel;
    support = new PropertyChangeSupport(this);

    serverModel.addPropertyListener(EventType.SUCCESSFUL_SHOP_CREATION.toString(), this::createShopResponse);
    serverModel.addPropertyListener(EventType.UNSUCCESSFUL_SHOP_CREATION.toString(), this::createShopResponse);
    serverModel.addPropertyListener(EventType.ALL_SHOPS_LIST.toString(), this::allShopsResponse);
    serverModel.addPropertyListener(EventType.SHOP_DELETED.toString(), this::shopDeleteResponse);
    serverModel.addPropertyListener(EventType.SHOP_DELETE_ERROR.toString(), this::shopDeleteResponse);
  }

  private void shopDeleteResponse(PropertyChangeEvent propertyChangeEvent)
  {
    String clientId = (String) propertyChangeEvent.getNewValue();
    String message = propertyChangeEvent.getPropertyName();
    try {
      AdminManageShopClientCallback clientToCallback = hashMap.remove(clientId);
      if (clientToCallback != null) {
        clientToCallback.deleteShopResponse(message);
      }
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  private void allShopsResponse(PropertyChangeEvent event) {
    String clientId = (String) event.getOldValue();
    ArrayList<Shop> allShops = (ArrayList<Shop>) event.getNewValue();

    try {
      AdminManageShopClientCallback clientToCallback = hashMap.remove(clientId);
      if (clientToCallback != null) {
        clientToCallback.allShopsResponse(allShops);
      }
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void createShop(String city, String street,String zipCode, String clientId, AdminManageShopClientCallback adminManageShopClientCallback) {
    hashMap.put(clientId, adminManageShopClientCallback);
    serverModel.createShop(city, street,zipCode, clientId);
  }

  @Override public void getAllShops(String clientId,
      AdminManageShopClientCallback adminManageShopClientCallback) throws RemoteException
  {
    hashMap.put(clientId, adminManageShopClientCallback);
    serverModel.getAllShops(clientId);
  }

  @Override public void deleteShop(String clientId, Shop shop,
      AdminManageShopClientCallback adminManageShopClientCallback)
      throws RemoteException
  {
    hashMap.put(clientId, adminManageShopClientCallback);
    serverModel.deleteShop(clientId, shop);
  }

  private void createShopResponse(PropertyChangeEvent event){

    String clientId = (String) event.getOldValue();
    Shop createdShop = (Shop) event.getNewValue();
    EventType eventType = null;
    if (event.getPropertyName().equals(EventType.UNSUCCESSFUL_SHOP_CREATION.toString()))
      eventType = EventType.UNSUCCESSFUL_SHOP_CREATION;
    else if (event.getPropertyName().equals(EventType.SUCCESSFUL_SHOP_CREATION.toString()))
      eventType = EventType.SUCCESSFUL_SHOP_CREATION;

    for (Map.Entry<String, AdminManageShopClientCallback> entry : hashMap.entrySet()) {
      String key = entry.getKey();
      AdminManageShopClientCallback clientCallback = entry.getValue();
      // ...
      if (key != null && key.equals(clientId)) {
        if (eventType == EventType.SUCCESSFUL_SHOP_CREATION) {
          try {
            clientCallback.successCreateShopResponse(createdShop);
            hashMap.remove(key);
          } catch (RemoteException e) {
            e.printStackTrace();
          }
        } else if (eventType == EventType.UNSUCCESSFUL_SHOP_CREATION) {
          try {
            clientCallback.errorCreateShopResponse();
            hashMap.remove(key);
          } catch (RemoteException e) {
            e.printStackTrace();
          }
        }
      }
    }
  }
}
