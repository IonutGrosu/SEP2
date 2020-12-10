package warehouse.persistence.manageUser;

import warehouse.shared.transferObjects.User;

import java.util.ArrayList;

public interface ManageUserDAO
{
  boolean checkNewUser(String username);
  int createUser(String firstName, String lastName, String username, String password, String position);
  ArrayList<User> getAllUsers();
}
