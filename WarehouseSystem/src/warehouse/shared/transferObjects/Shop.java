package warehouse.shared.transferObjects;

import java.io.Serializable;

public class Shop implements Serializable
{
  private int id;
  private String city;
  private String street;

  public Shop(int id, String city, String street)
  {
    this.id = id;
    this.city = city;
    this.street = street;
  }

  public int getId()
  {
    return id;
  }

  public String getCity()
  {
    return city;
  }

  public String getStreet()
  {
    return street;
  }
}
