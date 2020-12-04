package warehouse.persistence.manageUser;

public interface ManageUserDAO
{
  boolean checkNewUser(String username);
  int createUser(String firstName, String lastName, String username, String password, String position);
}
