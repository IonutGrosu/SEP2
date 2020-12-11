package warehouse.server.model;

import warehouse.persistence.login.LoginDAO;
import warehouse.persistence.login.LoginDAO;
import warehouse.persistence.manageShops.ManageShopsDAO;
import warehouse.persistence.manageUser.ManageUserDAO;
import warehouse.shared.transferObjects.EventType;
import warehouse.shared.transferObjects.Shop;
import warehouse.shared.transferObjects.User;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ServerModelImpl implements ServerModel
{
  private LoginDAO loginDAO;
  private ManageUserDAO manageUserDAO;
  private ManageShopsDAO manageShopsDAO;
  private PropertyChangeSupport support;

  public ServerModelImpl(LoginDAO loginDAO, ManageUserDAO manageUserDAO, ManageShopsDAO manageShopsDAO)
  {
    this.loginDAO = loginDAO;
    this.manageUserDAO = manageUserDAO;
    this.manageShopsDAO = manageShopsDAO;
    support = new PropertyChangeSupport(this);
  }

  @Override
  public boolean checkCredentials(String username, String password) {
    //return loginDAO.checkCredentials(username, password);
    return true;
  }

  @Override public void newUser(String firstName, String lastName,
      String username, String password, String position)
  {
    boolean userExists = manageUserDAO.checkNewUser(username);
    if(userExists)
    {
      support.firePropertyChange("alreadyExistingUsername", username, null);
    }
    else
    {
      int id = -1;
      id = manageUserDAO.createUser(firstName, lastName, username, password, position);
      if(id != -1)
      {
        support.firePropertyChange("userCreated", username, new User(id, firstName, lastName, position));
      }
      else
      {
        support.firePropertyChange("errorCreatingUserInDatabase", username, null);
      }
    }
  }

  @Override
  public void createShop(String city, String street, String zipCode, String clientId) {
    boolean shopExistingBefore = manageShopsDAO.checkIfShopExists(city, street);
    int id = 0;
    if (!shopExistingBefore) {
      id = manageShopsDAO.createShop(city, street, zipCode);
    }

    boolean shopExistingAfter = manageShopsDAO.checkIfShopExists(city, street);
    if (!shopExistingBefore && shopExistingAfter) {
      support.firePropertyChange(EventType.SUCCESSFUL_SHOP_CREATION.toString(), clientId, new Shop(id, city, street, zipCode));
    } else {
      support.firePropertyChange(EventType.UNSUCCESSFUL_SHOP_CREATION.toString(), clientId, null);
    }
  }

  @Override public void getAllShops(String clientId)
  {
    ArrayList<Shop> allShops = manageShopsDAO.getAllShops();
    System.out.println("A ajuns in get all shops servermodel");
    support.firePropertyChange(EventType.ALL_SHOPS_LIST.toString(), clientId, allShops);
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
