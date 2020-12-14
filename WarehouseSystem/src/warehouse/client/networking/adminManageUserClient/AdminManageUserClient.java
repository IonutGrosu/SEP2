package warehouse.client.networking.adminManageUserClient;

import warehouse.shared.util.PropertyChangeSubject;

public interface AdminManageUserClient extends PropertyChangeSubject
{
  void newUser(String firstName, String lastName, String username, String password, String position);
  void getAllUsers(String clientUsernameId);
}
