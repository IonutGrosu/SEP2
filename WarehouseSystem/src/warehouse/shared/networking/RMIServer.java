package warehouse.shared.networking;

import java.rmi.Remote;

public interface RMIServer extends Remote {
    void login(String username, String password);
}
