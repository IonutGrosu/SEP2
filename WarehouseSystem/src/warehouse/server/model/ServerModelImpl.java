package warehouse.server.model;

import warehouse.persistence.login.LoginDAO;
import warehouse.persistence.login.LoginDAO;

import java.beans.PropertyChangeListener;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ServerModelImpl implements ServerModel
{
  private LoginDAO loginDAO;
  private PropertyChangeSupport support;

  public ServerModelImpl(LoginDAO loginDAO)
  {
    this.loginDAO = loginDAO;
  }


  @Override
  public boolean checkCredentials(String username, String password) {
    return loginDAO.checkCredentials(username, password);
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
