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
          "SELECT * FROM shops WHERE city = ? AND street = ?");
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
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM shops");
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        int id = resultSet.getInt("shopid");
        String city = resultSet.getString("city");
        String street = resultSet.getString("street");
        String zipCode = resultSet.getString("zipcode");
        System.out.println("*" + id + " " + city + " " +street + " " + zipCode);
        allShops.add(new Shop(id, city, street, zipCode));
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return allShops;
  }

  @Override
  public int createShop(String city, String street, String zipCode) {
    int returnInt = 0;
    try (Connection connection = jdbcController.getConnection()) {
      PreparedStatement statement = connection
              .prepareStatement("INSERT INTO shops VALUES (default, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setString(1, city);
      statement.setString(2, street);
      statement.setString(3, zipCode);
      statement.executeUpdate();
      ResultSet resultSet = statement.getGeneratedKeys();
      if (resultSet.next())
        returnInt = resultSet.getInt(1);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return returnInt;
  }

  //TODO delete this before hand in pls
  public void deleteAllFromShops() {
    try(Connection connection = jdbcController.getConnection()) {
      PreparedStatement statement = connection.prepareStatement("DELETE FROM shops");
      statement.executeUpdate();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

}
