package warehouse.client.networking.loginClient;

import warehouse.shared.transferObjects.User;

public interface LoginClient
{
  User login(String username, String password);
}
