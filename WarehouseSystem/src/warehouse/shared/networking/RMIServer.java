package warehouse.shared.networking;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIServer extends Remote {
    void login(String username, String password) throws RemoteException;
}
