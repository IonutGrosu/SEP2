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
    String url = "hattie.db.elephantsql.com:5432/wzzxaats?currentSchema=SEP2_Database";
    String username = "postgres";
    String password = "9533";
    return DriverManager.getConnection(url, username, password);
  }

}

