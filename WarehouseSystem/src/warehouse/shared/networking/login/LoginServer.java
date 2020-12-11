package warehouse.shared.networking.login;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LoginServer extends Remote
{
  boolean login(String username, String password) throws RemoteException;
}
