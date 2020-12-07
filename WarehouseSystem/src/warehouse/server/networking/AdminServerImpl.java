package warehouse.server.networking;

import warehouse.server.model.ServerModel;
import warehouse.shared.networking.AdminServer;
import warehouse.shared.transferObjects.EventType;
import warehouse.shared.networking.ClientCallback;
import warehouse.shared.transferObjects.Shop;
import warehouse.shared.transferObjects.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class AdminServerImpl implements AdminServer
{
  private ServerModel serverModel;
  private PropertyChangeSupport support;
  private Map<String, ClientCallback> hashMap = new HashMap<>();

  public AdminServerImpl(ServerModel serverModel)
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
    serverModel.addPropertyListener("userCreated", this::createUserResponse);
    serverModel.addPropertyListener("alreadyExistingUsername", this::createUserResponse);
    serverModel.addPropertyListener("errorCreatingUserInDatabase", this::createUserResponse);

    serverModel.addPropertyListener(EventType.SUCCESSFUL_SHOP_CREATION.toString(), this::createShopResponse);
    serverModel.addPropertyListener(EventType.UNSUCCESSFUL_SHOP_CREATION.toString(), this::createShopResponse);
  }

  private void createUserResponse(PropertyChangeEvent propertyChangeEvent)
  {
    String username = (String) propertyChangeEvent.getOldValue();
    String eventName = propertyChangeEvent.getPropertyName();
    for(Map.Entry<String, ClientCallback> set : hashMap.entrySet())
    {
      if(set.getKey().equals(username) && eventName.equals("userCreated"))
      {
        try
        {
          set.getValue().successCreateUserResponse((User) propertyChangeEvent.getNewValue());
          hashMap.remove(set.getKey());
          break;
        }
        catch (RemoteException e)
        {
          e.printStackTrace();
        }
      }

      if(set.getKey().equals(username) && (eventName.equals("alreadyExistingUsername") || eventName.equals("errorCreatingUserInDatabase")))
        {
          try
          {
            set.getValue().errorCreateUserResponse(eventName);
            hashMap.remove(set.getKey());
            break;
          }
          catch (RemoteException e)
          {
            e.printStackTrace();
          }
        }

    }
  }

  @Override public void newUser(String firstName, String lastName,
      String username, String password, String position, ClientCallback clientCallback)
  {
    hashMap.put(username, clientCallback);
    serverModel.newUser(firstName, lastName, username, password, position);
  }

  @Override
  public void createShop(String city, String street, String clientId, ClientCallback clientCallback) {
    hashMap.put(clientId, clientCallback);
    serverModel.createShop(city, street, clientId);
  }

  private void createShopResponse(PropertyChangeEvent event){

    String clientId = (String) event.getOldValue();
    Shop createdShop = (Shop) event.getNewValue();
    EventType eventType = null;
    if (event.getPropertyName().equals(EventType.UNSUCCESSFUL_SHOP_CREATION.toString()))
      eventType = EventType.UNSUCCESSFUL_SHOP_CREATION;
    else if (event.getPropertyName().equals(EventType.SUCCESSFUL_SHOP_CREATION.toString()))
      eventType = EventType.SUCCESSFUL_SHOP_CREATION;

    for (Map.Entry<String, ClientCallback> entry : hashMap.entrySet()) {
      String key = entry.getKey();
      ClientCallback clientCallback = entry.getValue();
      // ...
      if (key.equals(clientId))
        if (eventType.equals(EventType.SUCCESSFUL_SHOP_CREATION)) {
          try {
            clientCallback.successCreateShopResponse(createdShop);
          } catch (RemoteException e) {
            e.printStackTrace();
          }
        }
        else if (eventType.equals(EventType.UNSUCCESSFUL_SHOP_CREATION)) {
          try {
            clientCallback.errorCreateShopResponse();
          } catch (RemoteException e) {
            e.printStackTrace();
          }
        }
    }
  }
}
