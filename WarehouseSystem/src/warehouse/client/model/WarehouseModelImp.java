package warehouse.client.model;

import warehouse.client.networking.Client;

public class WarehouseModelImp implements WarehouseModel
{
  private Client client;

  public WarehouseModelImp(Client client)
  {
    this.client = client;
  }
}
