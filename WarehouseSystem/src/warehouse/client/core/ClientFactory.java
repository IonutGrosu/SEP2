package warehouse.client.core;

import warehouse.client.networking.Client;
import warehouse.client.networking.RMIClientImpl;

public class ClientFactory {
    private Client client;

    public Client getClient() {
        if (client == null) client = new RMIClientImpl();
        return client;
    }
}
