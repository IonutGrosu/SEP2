package warehouse.client.networking.adminManageUserClient;

public interface AdminManageUserClient
{
  void newUser(String firstName, String lastName, String username, String password, String position);
}
