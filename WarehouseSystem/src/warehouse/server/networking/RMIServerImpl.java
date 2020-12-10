package warehouse.server.networking;

import warehouse.server.model.ServerModel;
import warehouse.shared.networking.ClientCallback;
import warehouse.shared.networking.adminManageShop.AdminManageShopServer;
import warehouse.shared.networking.adminManageUser.AdminManageUserServer;
import warehouse.shared.networking.login.LoginServer;
import warehouse.shared.networking.RMIServer;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class RMIServerImpl implements RMIServer
{
  private ServerModel serverModel;
//  private Map<ClientCallback, PropertyChangeListener> listeners = new HashMap<>();

  public RMIServerImpl(ServerModel serverModel) throws RemoteException
  {
    UnicastRemoteObject.exportObject(this, 0);
    this.serverModel = serverModel;
  }

  @Override public LoginServer getLoginServer() throws RemoteException
  {
    return new LoginServerImpl(serverModel);
  }

  @Override public AdminManageUserServer getAdminManageUserServer()
      throws RemoteException
  {
    return new AdminManageUserServerImpl(serverModel);
  }

  @Override public AdminManageShopServer getAdminManageShopServer()
      throws RemoteException
  {
    return new AdminManageShopServerImpl(serverModel);
  }
}
