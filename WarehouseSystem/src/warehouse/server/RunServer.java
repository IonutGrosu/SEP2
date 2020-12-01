package warehouse.server;

import warehouse.client.networking.RMIClientImpl;
import warehouse.persistence.LoginDAO;
import warehouse.persistence.LoginDAOImpl;
import warehouse.server.model.ServerModel;
import warehouse.server.model.ServerModelImpl;
import warehouse.server.networking.RMIServerImpl;
import warehouse.shared.networking.ClientCallback;
import warehouse.shared.networking.RMIServer;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RunServer
{
  public static void main(String[] args)
      throws RemoteException, AlreadyBoundException
  {
    LoginDAO loginDAO = new LoginDAOImpl();
    ServerModel serverModel = new ServerModelImpl(loginDAO);
    RMIServer server = new RMIServerImpl(serverModel);
    Registry registry = LocateRegistry.createRegistry(9999);
    registry.bind("Server", server);
    System.out.println("Server starting");
  }
}
