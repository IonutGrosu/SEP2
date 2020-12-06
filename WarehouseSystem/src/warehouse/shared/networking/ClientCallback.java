package warehouse.shared.networking;

import warehouse.shared.transferObjects.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCallback extends Remote {
    void loginResponse(boolean b) throws RemoteException;
    void successCreateUserResponse(User user) throws RemoteException;
    void errorCreateUserResponse() throws RemoteException;
}
