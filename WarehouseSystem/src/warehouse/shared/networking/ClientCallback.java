package warehouse.shared.networking;

import java.rmi.Remote;

public interface ClientCallback extends Remote {
    boolean loginResponse();
}
