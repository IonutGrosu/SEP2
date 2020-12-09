package warehouse.client.networking;

import warehouse.shared.util.PropertyChangeSubject;

import java.rmi.RemoteException;

public interface Client extends PropertyChangeSubject
{
    void login(String username, String password) throws RemoteException;
    void newUser(String firstName, String lastName, String username, String password, String position);
    void createShop(String city, String street,String zipCode, String clientId);
}
