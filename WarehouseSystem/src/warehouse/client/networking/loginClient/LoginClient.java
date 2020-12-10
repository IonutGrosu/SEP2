package warehouse.client.networking.loginClient;

import java.rmi.RemoteException;

public interface LoginClient
{
  void login(String username, String password) throws RemoteException;
}
