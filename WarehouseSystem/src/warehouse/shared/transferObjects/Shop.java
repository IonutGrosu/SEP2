package warehouse.shared.transferObjects;

import java.io.Serializable;

public class Shop implements Serializable
{
  private int id;
  private String city;
  private String street;
  private String zipCode;

  public Shop(int id, String city, String street, String zipCode)
  {
    this.id = id;
    this.city = city;
    this.street = street;
    this.zipCode = zipCode;
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

  public String getZipCode() {return zipCode;}
}
