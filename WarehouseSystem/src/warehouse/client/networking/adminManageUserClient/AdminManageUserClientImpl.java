package warehouse.client.networking.adminManageUserClient;

import warehouse.client.networking.Connection;
import warehouse.shared.networking.RMIServer;
import warehouse.shared.networking.adminManageUser.AdminManageUserClientCallback;
import warehouse.shared.transferObjects.EventType;
import warehouse.shared.transferObjects.User;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class AdminManageUserClientImpl implements AdminManageUserClient,
    AdminManageUserClientCallback
{
  private RMIServer rmiServer;
  private PropertyChangeSupport support;

  public AdminManageUserClientImpl()
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

  @Override public void newUser(String firstName, String lastName,
      String username, String password, String position)
  {
    try
    {
      rmiServer.getAdminManageUserServer().newUser(firstName, lastName, username, password, position, this);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void getAllUsers(String clientUsernameId)
  {
    try
    {
      rmiServer.getAdminManageUserServer().getAllUsers(clientUsernameId, this);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void successCreateUserResponse(User user)
  {
    support.firePropertyChange("userCreated",null,user);
  }


  @Override public void errorCreateUserResponse(String eventName)
  {
    support.firePropertyChange(eventName, null, null);
  }

  @Override public void allUsersResponse(ArrayList<User> allUsers)
      throws RemoteException
  {
    support.firePropertyChange(EventType.ALL_USERS_LIST.toString(), null, allUsers);
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
