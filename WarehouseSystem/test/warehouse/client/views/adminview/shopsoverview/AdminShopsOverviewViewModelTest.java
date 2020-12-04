package warehouse.client.views.adminview.shopsoverview;

import warehouse.client.model.WarehouseModel;
import warehouse.client.model.WarehouseModelImpl;
import warehouse.client.networking.Client;
import warehouse.client.networking.RMIClientImpl;
import warehouse.persistence.manageShops.ManageShopsDAO;
import warehouse.persistence.manageShops.ManageShopsDAOImpl;

import static org.junit.jupiter.api.Assertions.*;

class AdminShopsOverviewViewModelTest
{

  Client client = new RMIClientImpl();
  WarehouseModel warehouseModel = new WarehouseModelImpl(client);

  @org.junit.jupiter.api.Test
  public void creatingViewModel(){
    AdminShopsOverviewViewModel adminShopsOverviewViewModel = new AdminShopsOverviewViewModel(warehouseModel);
  }
  @org.junit.jupiter.api.Test
  public void creatingViewController(){
    AdminShopsOverviewViewController adminShopsOverviewViewController = new AdminShopsOverviewViewController();
  }
}