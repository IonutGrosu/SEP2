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
    try (Connection connection = jdbcController.getConnection())
    {
      PreparedStatement statement =
          connection.prepareStatement("INSERT INTO users(firstname,secondname,username,password,usertype) values (?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setString(1, firstName);
      statement.setString(2, lastName);
      statement.setString(3, username);
      statement.setString(4, password);
      statement.setString(5, position);
      System.out.println(statement);
      statement.executeUpdate();
      ResultSet keys = statement.getGeneratedKeys();
      if(keys.next())
      {
        return keys.getInt(1);
      }
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return -1;
  }
}
