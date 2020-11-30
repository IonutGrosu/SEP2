package warehouse.client.networking;

import warehouse.shared.networking.ClientCallback;
import warehouse.shared.networking.RMIServer;

public class RMIClientImpl implements Client, ClientCallback {
    private RMIServer rmiServer;

    public RMIClientImpl() {
    }

    @Override
    public void login(String username, String password) {

    }

    @Override
    public boolean loginResponse() {
        return false;
    }
}
