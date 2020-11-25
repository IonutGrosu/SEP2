package warehouse.server.networking;

import warehouse.server.model.ServerModel;
import warehouse.shared.networking.RMIServer;

public class RMIServerImpl implements RMIServer
{
  private ServerModel serverModel;

  public RMIServerImpl(ServerModel serverModel)
  {
    this.serverModel = serverModel;
  }
}
