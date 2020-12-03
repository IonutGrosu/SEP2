package warehouse.persistence;

import java.sql.*;

public class LoginDAOImpl implements LoginDAO {
    private static LoginDAOImpl instance;
    private JDBCController jdbcController;

    private LoginDAOImpl() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        jdbcController = JDBCController.getInstance();

    }

    public static synchronized LoginDAOImpl getInstance() {
        if (instance == null) {
            instance = new LoginDAOImpl();
        }
        return instance;
    }

    @Override
    public boolean checkCredentials(String username, String password) {
        boolean existingUsername = true;
        try (Connection connection = jdbcController.getConnection();) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                existingUsername = false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (existingUsername) return  true;
        return false;
    }

}