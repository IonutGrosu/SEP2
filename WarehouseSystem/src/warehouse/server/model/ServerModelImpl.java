package warehouse.server.model;

import warehouse.persistence.LoginDAO;

public class ServerModelImpl implements ServerModel
{
  private LoginDAO loginDAO;

  public ServerModelImpl(LoginDAO loginDAO)
  {
    this.loginDAO = loginDAO;
  }

  @Override
  public boolean checkCredentials(String username, String password) {
    return false;
  }
}
