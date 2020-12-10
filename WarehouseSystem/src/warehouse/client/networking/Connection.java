package warehouse.client.networking;

import warehouse.shared.networking.RMIServer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Connection
{
  private static RMIServer rmiServer;

  public static RMIServer getRmiServer()
  {
    if(rmiServer == null)
    {
      try
      {
        rmiServer = (RMIServer) LocateRegistry.getRegistry("localhost", 1099).lookup("Server");
      }
      catch (RemoteException | NotBoundException e)
      {
        e.printStackTrace();
      }
    }
    return rmiServer;
  }
}
