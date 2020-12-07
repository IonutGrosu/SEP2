package warehouse.client.model;

import warehouse.client.networking.Client;
import warehouse.shared.transferObjects.EventType;
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
  private String clientUsernameId;

  public WarehouseModelImpl(Client client)
  {
    this.client = client;
    support = new PropertyChangeSupport(this);
    //response for login
    client.addPropertyListener("loginResponse", this::loginResponse);
    client.addPropertyListener("errorResponse", this::loginResponse);
    //response for admin creating user -- Claudiu
    client.addPropertyListener("alreadyExistingUsername", this::createUserResponse);
    client.addPropertyListener("userCreated", this::createUserResponse);
    client.addPropertyListener("errorCreatingUserInDatabase", this::createUserResponse);
    //response for creation of shop --Ionut
    client.addPropertyListener(EventType.SUCCESSFUL_SHOP_CREATION.toString(), this::createShopResponse);
    client.addPropertyListener(EventType.UNSUCCESSFUL_SHOP_CREATION.toString(), this::createShopResponse);
  }

  private void createUserResponse(PropertyChangeEvent propertyChangeEvent)
  {
    support.firePropertyChange(propertyChangeEvent);
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
    clientUsernameId = username;
    try
    {
      client.login(username, password);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override
  public void createShop(String city, String street) {
    client.createShop(city, street, clientUsernameId);
  }

  private void createShopResponse(PropertyChangeEvent event) {
    support.firePropertyChange(event);
    client.addPropertyListener("userCreated", this::addCreatedUserToList);//TODO ask Maria what this is
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
