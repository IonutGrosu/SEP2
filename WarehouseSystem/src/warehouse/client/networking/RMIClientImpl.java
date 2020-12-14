package warehouse.client.networking;

import warehouse.client.networking.adminManageShopClient.AdminManageShopClient;
import warehouse.client.networking.adminManageShopClient.AdminManageShopClientImpl;
import warehouse.client.networking.adminManageUserClient.AdminManageUserClient;
import warehouse.client.networking.adminManageUserClient.AdminManageUserClientImpl;
import warehouse.client.networking.loginClient.LoginClient;
import warehouse.client.networking.loginClient.LoginClientImpl;
import warehouse.shared.networking.ClientCallback;
import warehouse.shared.networking.RMIServer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RMIClientImpl implements Client, ClientCallback
{
  private RMIServer rmiServer;
  private PropertyChangeSupport support;

  private LoginClient loginClient;
  private AdminManageShopClient adminManageShopClient;
  private AdminManageUserClient adminManageUserClient;


  public RMIClientImpl()
  {
    support = new PropertyChangeSupport(this);

  }

  @Override public LoginClient getLoginClient()
  {
    if (loginClient == null) loginClient = new LoginClientImpl();
    return loginClient;
  }

  @Override public AdminManageUserClient getAdminManageUserClient()
  {
    if (adminManageUserClient == null) adminManageUserClient = new AdminManageUserClientImpl();
    return adminManageUserClient;
  }

  @Override public AdminManageShopClient getAdminManageShopClient()
  {
    if (adminManageShopClient == null) adminManageShopClient = new AdminManageShopClientImpl();
    return adminManageShopClient;
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
