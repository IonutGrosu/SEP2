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

public class RMIClientImpl implements Client, ClientCallback
{
  private RMIServer rmiServer;
  private PropertyChangeSupport support;

  public RMIClientImpl()
  {
    support = new PropertyChangeSupport(this);
  }
  public void startClient(){
    Registry registry = null;
    try
    {
      registry = LocateRegistry.getRegistry("localhost", 9999);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    try
    {
      rmiServer = (RMIServer)registry.lookup("Server");
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
   rmiServer.login(username, password);
  }

  @Override public void loginResponse()
  {
    support.firePropertyChange("loginResponse", null, true);
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
