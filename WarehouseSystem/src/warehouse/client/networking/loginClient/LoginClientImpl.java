package warehouse.client.networking.loginClient;

import warehouse.client.networking.Connection;
import warehouse.shared.networking.RMIServer;
import warehouse.shared.networking.login.LoginClientCallback;

import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class LoginClientImpl implements LoginClient, LoginClientCallback
{
  private RMIServer rmiServer;
  private PropertyChangeSupport support;
  private boolean b;

  public LoginClientImpl()
  {
    try
    {
      UnicastRemoteObject.exportObject(this, 0);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    support = new PropertyChangeSupport(this);
    rmiServer = Connection.getRmiServer();
  }

  @Override public void login(String username, String password)
      throws RemoteException
  {
    System.out.println("Client Asks For Verification");
    b = rmiServer.getLoginServer().login(username, password);
  }

  @Override public void loginResponse(boolean b)
  {
    support.firePropertyChange("loginResponse", null, b);
  }
}
