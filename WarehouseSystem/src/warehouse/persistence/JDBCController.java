package warehouse.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCController {
  private static JDBCController instance;

  private JDBCController()
  {
  }

  public static JDBCController getInstance()
  {
    if (instance == null)
    {
      instance = new JDBCController();
    }
    return instance;
  }

  public Connection getConnection() throws SQLException
  {
    String url = "jdbc:postgresql://hattie.db.elephantsql.com:5432/wzzxaats?currentSchema=sep2database";
    String username = "wzzxaats";
    String password = "1-8RvGmhU--OEaBVP5hXi_7IqOYK-7Sp";
    return DriverManager.getConnection(url, username, password);
  }

}

