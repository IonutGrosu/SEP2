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

  @Override public int createShop(String city, String street)
  {
    int returnInt = 0;
      try (Connection connection = jdbcController.getConnection();)
      {
        PreparedStatement statement = connection
            .prepareStatement("INSERT INTO shops VALUES (default, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, city);
        statement.setString(2, street);
        statement.executeUpdate();
        ResultSet resultSet = statement.getGeneratedKeys();
        if (resultSet.next())
          returnInt = resultSet.getInt(1);
      }
      catch (SQLException throwables)
      {
        throwables.printStackTrace();
      }
      return returnInt;

  }

}
