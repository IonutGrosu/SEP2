package warehouse.persistence.login;

import warehouse.persistence.JDBCController;
import warehouse.shared.transferObjects.User;

import java.sql.*;

public class LoginDAOImpl implements LoginDAO
{
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
    public User checkCredentials(String username, String password) {
        User loggedInUser = null;
        try (Connection connection = jdbcController.getConnection();) {
            PreparedStatement checkCredentialsStatement = connection.prepareStatement("SELECT accountid FROM account WHERE username = ? AND password = ?");
            checkCredentialsStatement.setString(1, username);
            checkCredentialsStatement.setString(2, password);
            ResultSet credentialsCheckResult = checkCredentialsStatement.executeQuery();
            if (credentialsCheckResult.next()) {
                int accountId = credentialsCheckResult.getInt("accountid");
                PreparedStatement checkAccountIdInAdminTable = connection.prepareStatement("SELECT * FROM admin WHERE accountid = ?");
                checkAccountIdInAdminTable.setInt(1, accountId);
                ResultSet adminCheckResult = checkAccountIdInAdminTable.executeQuery();
                if (adminCheckResult.next()){
                    int adminId = adminCheckResult.getInt("adminid");
                    String firstName = adminCheckResult.getString("firstname");
                    String lastName = adminCheckResult.getString("lastname");
                    loggedInUser = new User(adminId, firstName, lastName, "ADMIN");
                } else {
                    PreparedStatement checkAccountIdInEmployeeTable = connection.prepareStatement("SELECT * FROM employee WHERE accountid = ?");
                    checkAccountIdInEmployeeTable.setInt(1, accountId);
                    ResultSet employeeCheckResult = checkAccountIdInEmployeeTable.executeQuery();
                    if (employeeCheckResult.next()) {
                        int employeeId = employeeCheckResult.getInt("employeeId");
                        String firstName = employeeCheckResult.getString("firstname");
                        String lastName = employeeCheckResult.getString("lastname");
                        loggedInUser = new User(employeeId, firstName, lastName, "EMPLOYEE");
                    } else {
                        PreparedStatement checkAccountIdInManagerTable = connection.prepareStatement("SELECT * FROM manager WHERE accountid = ?");
                        checkAccountIdInManagerTable.setInt(1, accountId);
                        ResultSet managerCheckResult = checkAccountIdInManagerTable.executeQuery();
                        if (managerCheckResult.next())  {
                            int managerId = managerCheckResult.getInt("managerId");
                            String firstName = managerCheckResult.getString("firstname");
                            String lastName = managerCheckResult.getString("lastname");
                            loggedInUser = new User(managerId, firstName, lastName, "MANAGER");
                        }
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return loggedInUser;
    }

}