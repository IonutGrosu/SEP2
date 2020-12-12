package warehouse.shared.networking.login;

import warehouse.shared.transferObjects.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LoginServer extends Remote
{
  User login(String username, String password) throws RemoteException;
}
