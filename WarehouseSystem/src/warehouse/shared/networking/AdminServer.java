package warehouse.shared.networking;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AdminServer extends Remote
{
  void newUser(String firstName, String lastName, String username, String password, String position) throws
      RemoteException;
}
