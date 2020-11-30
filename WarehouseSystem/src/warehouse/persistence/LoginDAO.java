package warehouse.persistence;

public interface LoginDAO {
    boolean checkCredentials(String username, String password);
}
