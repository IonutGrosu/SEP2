package warehouse.shared.networking;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCallback extends Remote {
    void loginResponse(boolean b) throws RemoteException;
}
