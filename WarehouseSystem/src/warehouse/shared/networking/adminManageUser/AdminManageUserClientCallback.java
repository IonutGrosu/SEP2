package warehouse.shared.networking.adminManageUser;

import warehouse.shared.transferObjects.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AdminManageUserClientCallback extends Remote
{
  void successCreateUserResponse(User user) throws RemoteException;
  void errorCreateUserResponse(String eventName) throws RemoteException;
}
