package warehouse.shared.transferObjects;

public class User
{
  private int id;
  private UserType userType;

  public User(int id, UserType userType)
  {
    this.id = id;
    this.userType = userType;
  }

  public int getId()
  {
    return id;
  }

  public UserType getUserType()
  {
    return userType;
  }
}
