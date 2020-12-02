package warehouse.server.model;

import warehouse.persistence.LoginDAO;

import java.beans.PropertyChangeListener;

public class ServerModelImpl implements ServerModel
{
  private LoginDAO loginDAO;

  public ServerModelImpl(LoginDAO loginDAO)
  {
    this.loginDAO = loginDAO;
  }

  @Override
  public boolean checkCredentials(String username, String password) {
//   CHECKING IN HERE
    return loginDAO.checkCredentials(username, password);
  }

  @Override public void addPropertyListener(String eventName,
      PropertyChangeListener listener)
  {

  }
}
