package warehouse.server.networking;

import warehouse.client.networking.RMIClientImpl;
import warehouse.server.model.ServerModel;
import warehouse.shared.networking.ClientCallback;
import warehouse.shared.networking.LoginServer;
import warehouse.shared.networking.RMIServer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class RMIServerImpl implements RMIServer
{
  private ServerModel serverModel;
  private Map<ClientCallback, PropertyChangeListener> listeners = new HashMap<>();

  public RMIServerImpl(ServerModel serverModel) throws RemoteException
  {
    UnicastRemoteObject.exportObject(this, 0);
    this.serverModel = serverModel;
  }

  @Override public LoginServer getLoginServer() throws RemoteException
  {
    return new LoginServerImpl(serverModel);
  }



//  public void registerClient(ClientCallback client)
//  {
//    PropertyChangeListener listener = new PropertyChangeListener()
//    {
//      @Override public void propertyChange(PropertyChangeEvent evt)
//      {
//        try
//        {
//          client.loginResponse((getLoginServer().getLoginResponse())evt.getNewValue());
//
//        }
//        catch (RemoteException e)
//        {
//          e.printStackTrace();
//          .removeListener("", this);
//        }
//      }
//    };
//  }
//  public void unRegisterClient(ClientCallback client) {
//    PropertyChangeListener listener = listeners.get(client);
//    if(listener != null) {
//      t.removeListener("NewLogEntry", listener);
//    }
//
//  }
}
