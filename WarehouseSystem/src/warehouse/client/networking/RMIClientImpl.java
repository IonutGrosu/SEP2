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


  public RMIClientImpl()
  {
    support = new PropertyChangeSupport(this);
  }

  @Override public LoginClient getLoginClient()
  {
    return new LoginClientImpl();
  }

  @Override public AdminManageUserClient getAdminManageUserClient()
  {
    return new AdminManageUserClientImpl();
  }

  @Override public AdminManageShopClient getAdminManageShopClient()
  {
    return new AdminManageShopClientImpl();
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
