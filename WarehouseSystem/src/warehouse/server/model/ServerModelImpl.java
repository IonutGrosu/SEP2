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
    return loginDAO.checkCredentials(username, password);
  }

  @Override public void newUser(String firstName, String lastName,
      String username, String password, String position)
  {
    boolean userExists = manageUserDAO.checkNewUser(username);
    if(userExists)
    {
      support.firePropertyChange("errorCreatingUser", username, null);
    }
    else
    {
      int id = manageUserDAO.createUser(firstName, lastName, username, password, position);
      support.firePropertyChange("userCreated", username, new User(id, firstName, lastName, position));
    }
  }

  @Override
  public void createShop(String city, String street, String clientId) {
    boolean existingShop = manageShopsDAO.checkIfShopExists(city, street);
    if (existingShop) {
      support.firePropertyChange(EventType.UNSUCCESSFUL_SHOP_CREATION.toString(), clientId, null);
    } else {
      int id = manageShopsDAO.createShop(city, street);
      support.firePropertyChange(EventType.SUCCESSFUL_SHOP_CREATION.toString(), clientId, new Shop(id, city, street));
    }
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
