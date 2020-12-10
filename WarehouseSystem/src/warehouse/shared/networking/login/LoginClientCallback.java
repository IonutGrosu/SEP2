package warehouse.shared.networking.login;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LoginClientCallback extends Remote
{
  void loginResponse(boolean b) throws RemoteException;
}
