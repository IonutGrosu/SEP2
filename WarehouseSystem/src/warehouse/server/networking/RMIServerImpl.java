package warehouse.server.networking;

import warehouse.server.model.ServerModel;
import warehouse.shared.networking.ClientCallback;
import warehouse.shared.networking.RMIServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIServerImpl implements RMIServer
{
  private ServerModel serverModel;
  private ClientCallback clientCallback;

  public RMIServerImpl(ServerModel serverModel) throws RemoteException
  {
    UnicastRemoteObject.exportObject(this, 0);
    this.serverModel = serverModel;
  }

  @Override
  public void login(String username, String password)  throws RemoteException {
     serverModel.checkCredentials(username, password);

  }
}
