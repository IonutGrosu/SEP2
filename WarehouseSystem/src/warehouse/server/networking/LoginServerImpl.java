package warehouse.server.networking;

import warehouse.client.networking.RMIClientImpl;
import warehouse.server.model.ServerModel;
import warehouse.shared.networking.ClientCallback;
import warehouse.shared.networking.LoginServer;
import warehouse.shared.util.PropertyChangeSubject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class LoginServerImpl implements LoginServer
{
  private ServerModel serverModel;
boolean b;

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

  @Override public boolean login(String username, String password)
      throws RemoteException
  {
    b = serverModel.checkCredentials(username, password);
  return serverModel.checkCredentials(username, password);
  }
  @Override
  public boolean getLoginResponse(){
    return b;
  }
}
