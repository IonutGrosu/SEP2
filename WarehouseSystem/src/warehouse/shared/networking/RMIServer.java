package warehouse.shared.networking;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIServer extends Remote {
    LoginServer getLoginServer() throws RemoteException;
    AdminServer getAdminServer() throws RemoteException;
}
