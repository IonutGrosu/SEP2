package warehouse.server;

import warehouse.client.networking.RMIClientImpl;
import warehouse.persistence.manageShops.ManageShopsDAO;
import warehouse.persistence.manageShops.ManageShopsDAOImpl;
import warehouse.persistence.manageUser.ManageUserDAO;
import warehouse.persistence.manageUser.ManageUserDAOImpl;
import warehouse.persistence.login.LoginDAO;
import warehouse.persistence.login.LoginDAOImpl;
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
    LoginDAO loginDAO = LoginDAOImpl.getInstance();
    ManageUserDAO manageUserDAO = ManageUserDAOImpl.getInstance();
    ManageShopsDAO manageShopsDAO = ManageShopsDAOImpl.getInstance();
    ServerModel serverModel = new ServerModelImpl(loginDAO, manageUserDAO, manageShopsDAO);
    RMIServer server = new RMIServerImpl(serverModel);

    Registry registry = LocateRegistry.createRegistry(1099);
    System.out.println("Registry Created");
    registry.bind("Server", server);
    System.out.println("Server starting");
  }
}
