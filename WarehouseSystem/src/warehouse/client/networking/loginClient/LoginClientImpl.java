package warehouse.client.networking.loginClient;

import warehouse.client.networking.Connection;
import warehouse.shared.networking.RMIServer;
import warehouse.shared.networking.login.LoginClientCallback;
import warehouse.shared.transferObjects.User;

import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class LoginClientImpl implements LoginClient, LoginClientCallback
{
  private RMIServer rmiServer;

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
    rmiServer = Connection.getRmiServer();
  }

  @Override public User login(String username, String password)
  {
    User userLoggedIn = null;
    try {
       userLoggedIn = rmiServer.getLoginServer().login(username, password);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
    return userLoggedIn;
  }
}
