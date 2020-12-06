package warehouse.server.networking;

import warehouse.server.model.ServerModel;
import warehouse.shared.networking.AdminServer;
import warehouse.shared.networking.ClientCallback;
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
    serverModel.addPropertyListener("errorCreatingUser", this::createUserResponse);
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
      else
      {
        if(set.getKey().equals(username) && eventName.equals("errorCreatingUser"))
        {
          try
          {
            set.getValue().errorCreateUserResponse();
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
  }

  @Override public void newUser(String firstName, String lastName,
      String username, String password, String position, ClientCallback clientCallback)
  {
    hashMap.put(username, clientCallback);
    serverModel.newUser(firstName, lastName, username, password, position);
  }
}
