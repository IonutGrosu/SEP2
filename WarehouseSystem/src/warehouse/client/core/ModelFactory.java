package warehouse.client.core;

import warehouse.client.model.WarehouseModel;
import warehouse.client.model.WarehouseModelImpl;

public class ModelFactory {
    private ClientFactory clientFactory;
    private WarehouseModel warehouseModel;

    public ModelFactory(ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
    }

    public WarehouseModel getWarehouseModel() {
        if (warehouseModel == null) warehouseModel = new WarehouseModelImpl(clientFactory.getClient());
        return warehouseModel;
    }
}
