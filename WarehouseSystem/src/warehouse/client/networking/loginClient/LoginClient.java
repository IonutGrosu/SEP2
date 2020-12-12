package warehouse.client.networking.loginClient;

import warehouse.shared.transferObjects.User;

import java.rmi.RemoteException;

public interface LoginClient
{
  User login(String username, String password);
}
