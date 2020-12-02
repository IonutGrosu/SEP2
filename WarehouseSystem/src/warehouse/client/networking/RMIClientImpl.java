package warehouse.client.networking;

import warehouse.shared.networking.ClientCallback;
import warehouse.shared.networking.RMIServer;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIClientImpl implements Client, ClientCallback
{
  private RMIServer rmiServer;
  private PropertyChangeSupport support;


  public RMIClientImpl()
  {
    support = new PropertyChangeSupport(this);
    Registry registry = null;
    try
    {
      registry = LocateRegistry.getRegistry("localhost", 9999);
      rmiServer = (RMIServer)registry.lookup("Server");
      System.out.println("Client Connected");
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    catch (NotBoundException e)
    {
      e.printStackTrace();
    }
  }
  @Override public void login(String username, String password)
      throws RemoteException
  {
   rmiServer.getLoginServer().login(username, password);
  }

  @Override public void loginResponse(boolean b)
  {
    support.firePropertyChange("loginResponse", null, b);
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
