package warehouse.shared.networking;

import java.beans.PropertyChangeEvent;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AdminServer extends Remote
{
  void newUser(String firstName, String lastName, String username, String password,
      String position, ClientCallback clientCallback) throws RemoteException;
  void createShop(String city, String street, String clientId, ClientCallback clientCallback) throws RemoteException;
}
