package warehouse.persistence.manageUser;

import warehouse.persistence.JDBCController;

import java.sql.*;

public class ManageUserDAOImpl implements ManageUserDAO
{
  private static ManageUserDAOImpl instance;
  private JDBCController jdbcController;

  private ManageUserDAOImpl()
  {
    try {
      DriverManager.registerDriver(new org.postgresql.Driver());
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    jdbcController = JDBCController.getInstance();
  }

  public static synchronized ManageUserDAOImpl getInstance()
  {
    if (instance == null) {
      instance = new ManageUserDAOImpl();
    }
    return instance;
  }

  @Override public boolean checkNewUser(String username)
  {
    boolean existingUsername = true;
    try (Connection connection = jdbcController.getConnection())
      {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
        statement.setString(1, username);
        System.out.println(statement);
        ResultSet resultSet = statement.executeQuery();
        if (!resultSet.next()) {
          existingUsername = false;
        }
      }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    if(existingUsername) return true;
    return false;
  }

  @Override public int createUser(String firstName, String lastName,
      String username, String password, String position)
  {
    int id;
    return 1;
  }
}
