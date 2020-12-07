package warehouse.shared.transferObjects;

import java.io.Serializable;

public class User implements Serializable
{
  private int id;
  private String firstName;
  private String lastName;
  private String position;

  public User(int id, String firstName, String lastName, String position)
  {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.position = position;
  }

  public int getId()
  {
    return id;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public String getPosition()
  {
    return position;
  }

  public String toString()
  {
    return id + " - " + firstName + " " + lastName + " - " + position;
  }
}
