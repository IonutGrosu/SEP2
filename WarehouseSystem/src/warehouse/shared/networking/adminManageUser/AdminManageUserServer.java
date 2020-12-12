package warehouse.shared.networking.adminManageUser;

import warehouse.shared.networking.ClientCallback;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AdminManageUserServer extends Remote
{
  void newUser(String firstName, String lastName, String username, String password,
      String position, AdminManageUserClientCallback adminManageUserClientCallback) throws RemoteException;
  void getAllUsers(String clientUsernameId, AdminManageUserClientCallback adminManageUserClientCallback) throws RemoteException;
}
