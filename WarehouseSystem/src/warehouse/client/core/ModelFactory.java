package warehouse.client.core;

import warehouse.client.model.WarehouseModel;

public class ModelFactory
{
  private ClientFactory clientFactory;
  private WarehouseModel warehouseModel;

  public ModelFactory(ClientFactory clientFactory)
  {
    this.clientFactory = clientFactory;
  }
}
