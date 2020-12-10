package warehouse.shared.networking;

import warehouse.shared.networking.adminManageShop.AdminManageShopServer;
import warehouse.shared.networking.adminManageUser.AdminManageUserServer;
import warehouse.shared.networking.login.LoginServer;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIServer extends Remote {
    LoginServer getLoginServer() throws RemoteException;
    AdminManageUserServer getAdminManageUserServer() throws RemoteException;
    AdminManageShopServer getAdminManageShopServer() throws RemoteException;
}
