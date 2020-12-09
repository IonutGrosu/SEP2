package warehouse.client.views.adminview.createshop;

import org.junit.jupiter.api.Test;
import warehouse.client.model.WarehouseModel;
import warehouse.client.model.WarehouseModelImpl;
import warehouse.client.networking.Client;
import warehouse.client.networking.RMIClientImpl;

class CreateShopViewModelTest {
    Client client = new RMIClientImpl();
    WarehouseModel model = new WarehouseModelImpl(client);

    @Test
    public void creatingViewModel(){
        CreateShopViewModel viewModel = new CreateShopViewModel(model);
    }

    @Test
    public void creatingViewController(){
        CreateShopViewController controller = new CreateShopViewController();
    }
}