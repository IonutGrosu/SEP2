package warehouse.server.networking;

import warehouse.server.model.ServerModel;
import warehouse.shared.networking.login.LoginServer;
import warehouse.shared.transferObjects.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class LoginServerImpl implements LoginServer
{
  private ServerModel serverModel;

  public LoginServerImpl(ServerModel serverModel)
  {
    try
    {
      UnicastRemoteObject.exportObject(this, 0);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    this.serverModel = serverModel;
  }

  @Override
  public User login(String username, String password)
          throws RemoteException {
    return serverModel.checkCredentials(username, password);
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
