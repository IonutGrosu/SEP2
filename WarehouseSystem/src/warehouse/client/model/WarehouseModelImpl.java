package warehouse.client.model;

import warehouse.client.networking.Client;
import warehouse.shared.transferObjects.EventType;
import warehouse.shared.transferObjects.Shop;
import warehouse.shared.transferObjects.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class WarehouseModelImpl implements WarehouseModel
{
  private Client client;
  private PropertyChangeSupport support;
  private String clientUsernameId;

  public WarehouseModelImpl(Client client)
  {
    this.client = client;
    support = new PropertyChangeSupport(this);
    //response for admin creating user -- Claudiu
    client.getAdminManageUserClient().addPropertyListener("alreadyExistingUsername", this::createUserResponse);
    client.getAdminManageUserClient().addPropertyListener("userCreated", this::createUserResponse);
    client.getAdminManageUserClient().addPropertyListener("errorCreatingUserInDatabase", this::createUserResponse);
    //response for creation of shop --Ionut
    client.getAdminManageShopClient().addPropertyListener(EventType.SUCCESSFUL_SHOP_CREATION.toString(), this::broadcastEvent);
    client.getAdminManageShopClient().addPropertyListener(EventType.UNSUCCESSFUL_SHOP_CREATION.toString(), this::broadcastEvent);
    //response for asking for all the shops --Ionut
    client.getAdminManageShopClient().addPropertyListener(EventType.ALL_SHOPS_LIST.toString(), this::broadcastEvent);
    client.getAdminManageUserClient().addPropertyListener(EventType.ALL_USERS_LIST.toString(), this::broadcastEvent);
    // response for successful or error deletion of the shop selected
    client.getAdminManageShopClient().addPropertyListener(EventType.SHOP_DELETED.toString(), this::deletionEvent);
    client.getAdminManageShopClient().addPropertyListener(EventType.SHOP_DELETE_ERROR.toString(), this::deletionEvent);
  }

  private void deletionEvent(PropertyChangeEvent propertyChangeEvent)
  {
    support.firePropertyChange(propertyChangeEvent);
  }

  private void createUserResponse(PropertyChangeEvent propertyChangeEvent)
  {
    support.firePropertyChange(propertyChangeEvent);
  }

  private void addCreatedUserToList(PropertyChangeEvent propertyChangeEvent)
  {
    support.firePropertyChange(propertyChangeEvent);
  }

  @Override public User login(String username, String password)
  {
    clientUsernameId = username;
    return client.getLoginClient().login(username, password);
  }

  @Override
  public void createShop(String city, String street, String zipCode) {
    client.getAdminManageShopClient().createShop(city, street, zipCode, clientUsernameId);
  }

  private void broadcastEvent(PropertyChangeEvent event) {
    support.firePropertyChange(event);
  }

  @Override public void setUserProperties(String firstName, String lastName,
      String username, String password, String position)
  {
    client.getAdminManageUserClient().newUser(firstName, lastName, username, password, position);
  }

  @Override
  public void getAllShops() {
    client.getAdminManageShopClient().getAllShops(clientUsernameId);
  }

  @Override public void getAllUsers()
  {
    client.getAdminManageUserClient().getAllUsers(clientUsernameId);
  }

  @Override public void deleteShop(Shop shop)
  {
    client.getAdminManageShopClient().deleteShop(clientUsernameId, shop);
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
