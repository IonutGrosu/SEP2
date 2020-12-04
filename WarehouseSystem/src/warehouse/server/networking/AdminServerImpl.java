package warehouse.server.networking;

import warehouse.server.model.ServerModel;
import warehouse.shared.networking.AdminServer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AdminServerImpl implements AdminServer
{
  private ServerModel serverModel;
  private PropertyChangeSupport support;

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

  }

  @Override public void newUser(String firstName, String lastName,
      String username, String password, String position)
  {
    serverModel.newUser(firstName, lastName, username, password, position);
  }
}
