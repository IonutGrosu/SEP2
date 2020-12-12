package warehouse.server.networking;

import warehouse.server.model.ServerModel;
import warehouse.shared.networking.ClientCallback;
import warehouse.shared.networking.adminManageShop.AdminManageShopClientCallback;
import warehouse.shared.networking.adminManageUser.AdminManageUserClientCallback;
import warehouse.shared.networking.adminManageUser.AdminManageUserServer;
import warehouse.shared.transferObjects.EventType;
import warehouse.shared.transferObjects.Shop;
import warehouse.shared.transferObjects.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdminManageUserServerImpl implements AdminManageUserServer
{
  private ServerModel serverModel;
  private PropertyChangeSupport support;
  private Map<String, AdminManageUserClientCallback> hashMap = new HashMap<>();

  public AdminManageUserServerImpl(ServerModel serverModel)
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
    serverModel.addPropertyListener(EventType.ALL_USERS_LIST.toString(), this::allUsersResponse);
  }

  private void allUsersResponse(PropertyChangeEvent propertyChangeEvent)
  {
    String clientUsernameId = (String) propertyChangeEvent.getOldValue();
    ArrayList<User> allUsers = (ArrayList<User>) propertyChangeEvent.getNewValue();

    try {
      AdminManageUserClientCallback clientToCallback = hashMap.remove(clientUsernameId);
      if (clientToCallback != null) {
        clientToCallback.allUsersResponse(allUsers);
      }
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  private void createUserResponse(PropertyChangeEvent propertyChangeEvent)
  {
    String username = (String) propertyChangeEvent.getOldValue();
    String eventName = propertyChangeEvent.getPropertyName();
    for(Map.Entry<String, AdminManageUserClientCallback> set : hashMap.entrySet())
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
      String username, String password, String position, AdminManageUserClientCallback adminManageUserClientCallback)
  {
    hashMap.put(username, adminManageUserClientCallback);
    serverModel.newUser(firstName, lastName, username, password, position);
  }

  @Override public void getAllUsers(String clientUsernameId, AdminManageUserClientCallback adminManageUserClientCallback)
  {
    hashMap.put(clientUsernameId, adminManageUserClientCallback);
    serverModel.getAllUsers(clientUsernameId);
  }
}
