package warehouse.shared.networking.adminManageUser;

import warehouse.shared.transferObjects.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface AdminManageUserClientCallback extends Remote
{
  void successCreateUserResponse(User user) throws RemoteException;
  void errorCreateUserResponse(String eventName) throws RemoteException;
  void allUsersResponse(ArrayList<User> allUsers) throws RemoteException;
}
