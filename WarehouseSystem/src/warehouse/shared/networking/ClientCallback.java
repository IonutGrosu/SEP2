package warehouse.shared.networking;

import warehouse.shared.transferObjects.Shop;
import warehouse.shared.transferObjects.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCallback extends Remote {
    void loginResponse(boolean b) throws RemoteException;
    void successCreateUserResponse(User user) throws RemoteException;
    void errorCreateUserResponse(String eventName) throws RemoteException;
    void successCreateShopResponse(Shop createdShop) throws RemoteException;
    void errorCreateShopResponse() throws RemoteException;
}
