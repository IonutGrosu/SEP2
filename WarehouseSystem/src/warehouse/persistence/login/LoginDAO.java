package warehouse.persistence.login;

public interface LoginDAO {
    boolean checkCredentials(String username, String password);
}
