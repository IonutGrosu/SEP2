package warehouse.persistence.manageShops;

import warehouse.persistence.JDBCController;
import warehouse.persistence.login.LoginDAOImpl;

import java.sql.*;

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
    boolean doesShopExist = false;
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

  @Override public boolean createShop(int id, String city, String street)
  {
    if (checkIfShopExists(city, street)){
      return false;
    }
    else {
      try (Connection connection = jdbcController.getConnection();)
      {
        PreparedStatement statement = connection
            .prepareStatement("INSERT INTO shops VALUES (default, ?, ?)");
        statement.setString(2, city);
        statement.setString(3, street);
        System.out.println(statement);
        ResultSet resultSet = statement.executeQuery();

      }
      catch (SQLException throwables)
      {
        throwables.printStackTrace();
      }
      return true;
    }
  }

}
