package warehouse.client.model;

import warehouse.client.networking.Client;
import warehouse.shared.transferObjects.User;
import warehouse.shared.transferObjects.UserType;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;

public class WarehouseModelImpl implements WarehouseModel
{
  private Client client;
  private PropertyChangeSupport support;
  private boolean temporaryBoolean;
  private User user;

  public WarehouseModelImpl(Client client)
  {
    this.client = client;
    support = new PropertyChangeSupport(this);
    client.addPropertyListener("loginResponse", this::loginResponse);
    client.addPropertyListener("errorResponse", this::loginResponse);
    client.addPropertyListener("userCreated", this::addCreatedUserToList);
  }

  private void addCreatedUserToList(PropertyChangeEvent propertyChangeEvent)
  {
    support.firePropertyChange(propertyChangeEvent);
  }


  private void loginResponse(PropertyChangeEvent propertyChangeEvent)
  {
    support.firePropertyChange(propertyChangeEvent);
    //    user = (User) propertyChangeEvent.getNewValue();
    //    switch (user.getUserType())
    //    {
    //      case MANAGER :
    //        support.firePropertyChange("MANAGER", null, user);
    //        break;
    //      case EMPLOYEE:
    //        support.firePropertyChange("EMPLOYEE", null, user);
    //        break;
    //      case ADMIN:
    //        support.firePropertyChange("ADMIN", null, user);
    //        break;
    //    }
  }

  @Override public void login(String username, String password)
  {
    try
    {
      client.login(username, password);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void setUserProperties(String firstName, String lastName,
      String username, String password, String position)
  {
    //client.newUser(firstName, lastName, username, password, position);
  }

  @Override public void addPropertyListener(String eventName,
      PropertyChangeListener listener)
  {
    if (eventName == null)
    {
      support.addPropertyChangeListener(listener);
    }
    else
    {
      support.addPropertyChangeListener(eventName, listener);
    }
  }
}
