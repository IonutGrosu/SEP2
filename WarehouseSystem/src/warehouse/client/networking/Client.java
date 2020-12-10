package warehouse.client.networking;

import warehouse.client.networking.adminManageShopClient.AdminManageShopClient;
import warehouse.client.networking.adminManageUserClient.AdminManageUserClient;
import warehouse.client.networking.loginClient.LoginClient;
import warehouse.shared.util.PropertyChangeSubject;

public interface Client extends PropertyChangeSubject
{
  LoginClient getLoginClient();
  AdminManageUserClient getAdminManageUserClient();
  AdminManageShopClient getAdminManageShopClient();
}
