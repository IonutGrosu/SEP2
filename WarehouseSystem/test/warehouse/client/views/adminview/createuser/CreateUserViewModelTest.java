package warehouse.client.views.adminview.createuser;

import org.junit.jupiter.api.Test;
import warehouse.client.model.WarehouseModel;
import warehouse.client.model.WarehouseModelImpl;
import warehouse.client.networking.Client;
import warehouse.client.networking.RMIClientImpl;

import static org.junit.jupiter.api.Assertions.*;

class CreateUserViewModelTest
{
  Client client = new RMIClientImpl();
  WarehouseModel warehouseModel = new WarehouseModelImpl(client);

  @Test
  public void creatingViewModel(){
    CreateUserViewModel viewModel = new CreateUserViewModel(warehouseModel);
  }

  @Test
  public void creatingViewController(){
    CreateUserViewController controller = new CreateUserViewController();
  }
}