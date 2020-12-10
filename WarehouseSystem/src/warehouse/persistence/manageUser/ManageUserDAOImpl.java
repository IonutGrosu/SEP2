package warehouse.persistence.manageUser;

import warehouse.persistence.JDBCController;
import warehouse.shared.transferObjects.User;

import java.sql.*;
import java.util.ArrayList;

public class ManageUserDAOImpl implements ManageUserDAO
{
  private static ManageUserDAOImpl instance;
  private JDBCController jdbcController;

  private ManageUserDAOImpl()
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

  public static synchronized ManageUserDAOImpl getInstance()
  {
    if (instance == null)
    {
      instance = new ManageUserDAOImpl();
    }
    return instance;
  }

  @Override public boolean checkNewUser(String username)
  {
    boolean existingUsername = true;
    try (Connection connection = jdbcController.getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM account WHERE username = ?");
      statement.setString(1, username);
      System.out.println(statement);
      ResultSet resultSet = statement.executeQuery();
      if (!resultSet.next())
      {
        existingUsername = false;
      }
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    if (existingUsername)
      return true;
    return false;
  }

  @Override public int createUser(String firstName, String lastName,
      String username, String password, String position)
  {
    try (Connection connection = jdbcController.getConnection())
    {

      //-------------------------------------------------------------------
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO account VALUES (default, ?, ?, ?)",
          PreparedStatement.RETURN_GENERATED_KEYS);
      if(position.equalsIgnoreCase("ADMIN")){
        statement.setInt(1, 1);
      }
      else if (position.equalsIgnoreCase("MANAGER")){
        statement.setInt(1, 2);

      }
      else if (position.equalsIgnoreCase("EMPLOYEE")){
        statement.setInt(1, 3);
      }
      statement.setString(2, username);
      statement.setString(3, password);
      System.out.println(statement);
      statement.executeUpdate();
      ResultSet keys = statement.getGeneratedKeys();

      if(position.equalsIgnoreCase("ADMIN")){
        PreparedStatement statement2 = connection.prepareStatement(
            "INSERT INTO admin VALUES ((SELECT accountid FROM account WHERE username = ? ), default, ?, ?)");
        statement2.setString(1, username);
        statement2.setString(2, firstName);
        statement2.setString(3, lastName);
        statement2.executeUpdate();
      }
      else if (position.equalsIgnoreCase("MANAGER"))
      {
        PreparedStatement statement2 = connection.prepareStatement(
            "INSERT INTO manager VALUES ((SELECT accountid FROM account WHERE username = ? ), default, ?, ?)");
        statement2.setString(1, username);
        statement2.setString(2, firstName);
        statement2.setString(3, lastName);
        statement2.executeUpdate();
      }
      else if (position.equalsIgnoreCase("EMPLOYEE")){
        PreparedStatement statement2 = connection.prepareStatement(
            "INSERT INTO employee VALUES ((SELECT accountid FROM account WHERE username = ? ), default, ?, ?)");
        statement2.setString(1, username);
        statement2.setString(2, firstName);
        statement2.setString(3, lastName);
        statement2.executeUpdate();
      }
      if (keys.next())
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

  @Override public ArrayList<User> getAllUsers()
  {
    ArrayList<User> arrayList = new ArrayList<>();

    try (Connection connection = jdbcController.getConnection())
    {

      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM admin");
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next() == true)
      {
        User temp = new User(resultSet.getInt(1), resultSet.getString(3),
            resultSet.getString(4), "ADMIN");
        arrayList.add(temp);
      }
      PreparedStatement statement2 = connection
          .prepareStatement("SELECT * FROM manager");
      ResultSet resultSet2 = statement2.executeQuery();
      while (resultSet2.next() == true)
      {
        User temp = new User(resultSet2.getInt(1), resultSet2.getString(3),
            resultSet2.getString(4), "MANAGER");
        arrayList.add(temp);
      }
      PreparedStatement statement3 = connection
          .prepareStatement("SELECT * FROM employee");
      ResultSet resultSet3 = statement3.executeQuery();
      while (resultSet3.next() == true)
      {
        User temp = new User(resultSet3.getInt(1), resultSet3.getString(3),
            resultSet3.getString(4), "EMPLOYEE");
        arrayList.add(temp);
      }
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }

    return arrayList;
  }
}
