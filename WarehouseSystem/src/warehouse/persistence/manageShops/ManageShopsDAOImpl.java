package warehouse.persistence.manageShops;

import warehouse.persistence.JDBCController;
import warehouse.persistence.login.LoginDAOImpl;
import warehouse.shared.transferObjects.Shop;

import java.sql.*;
import java.util.ArrayList;

public class ManageShopsDAOImpl implements ManageShopsDAO
{
  private static ManageShopsDAOImpl instance;
  private JDBCController jdbcController;

  private ManageShopsDAOImpl()
  {
    try
    {
      DriverManager.registerDriver(new org.postgresql.Driver());
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    jdbcController = JDBCController.getInstance();

  }

  public static synchronized ManageShopsDAOImpl getInstance()
  {
    if (instance == null)
    {
      instance = new ManageShopsDAOImpl();
    }
    return instance;
  }

  @Override public boolean checkIfShopExists(String city, String street)
  {
    boolean doesShopExist = true;
    try (Connection connection = jdbcController.getConnection();)
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM shop WHERE shop.zipcode = "
              + "(SELECT city.zipcode FROM city WHERE cityname = ?) AND streetname = ?");
      statement.setString(1, city);
      statement.setString(2, street);
      ResultSet resultSet = statement.executeQuery();
      if (!resultSet.next())
      {
        doesShopExist = false;
      }
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    if (doesShopExist){
      return  true;
    }
    else {
      return false;
    }

  }

  @Override
  public ArrayList<Shop> getAllShops() {
    ArrayList<Shop> allShops = new ArrayList<>();

    try (Connection connection = jdbcController.getConnection()) {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM (SELECT * FROM shop FULL OUTER JOIN city ON shop.zipcode = city.zipcode) \n"
          + "as sub WHERE sub.streetname is not null;");
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        int id = resultSet.getInt("shopid");
        String streetname = resultSet.getString("streetname");
        String zipcode = resultSet.getString(3);
        String city = resultSet.getString("cityname");
        System.out.println("*" + id + " " + city + " " +streetname + " " + zipcode);
        allShops.add(new Shop(id, city, streetname, zipcode));
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return allShops;
  }

  @Override public void deleteShop(Shop shop)
  {
    System.out.println("shop to delete: " + shop);
    try(Connection connection = jdbcController.getConnection()) {
      PreparedStatement statement = connection.prepareStatement("DELETE FROM shop WHERE shopid = ?");
      statement.setInt(1, shop.getId());
      statement.executeUpdate();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  @Override
  public int createShop(String city, String street, String zipCode) {
    int returnInt = 0;
    try (Connection connection = jdbcController.getConnection()) {
      PreparedStatement st = connection
          .prepareStatement("SELECT * FROM city WHERE zipcode = ?");
      st.setInt(1, Integer.parseInt(zipCode));
      ResultSet rs = st.executeQuery();
      if (!rs.next()){
        PreparedStatement statement1 = connection
            .prepareStatement("INSERT INTO city VALUES (?, ?)");
        System.out.println(statement1);
        statement1.setString(1, city);
        statement1.setInt(2, Integer.parseInt(zipCode));
        statement1.executeUpdate();
      }
      //--------------------------------------------------
      PreparedStatement statement2 = connection
              .prepareStatement("INSERT INTO shop VALUES (default, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
      System.out.println(statement2);
      statement2.setString(1, street);
      statement2.setInt(2, Integer.parseInt(zipCode));
      statement2.executeUpdate();
      ResultSet resultSet = statement2.getGeneratedKeys();
      if (resultSet.next())
        returnInt = resultSet.getInt(1);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return returnInt;
  }

  public void deleteAllShops  () {
    try(Connection connection = jdbcController.getConnection()) {
      PreparedStatement statement = connection.prepareStatement("DELETE FROM shop");
      statement.executeUpdate();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

}
