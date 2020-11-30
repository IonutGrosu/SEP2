package warehouse.client.model;

import warehouse.client.networking.Client;

public class WarehouseModelImpl implements WarehouseModel
{
  private Client client;

  public WarehouseModelImpl(Client client)
  {
    this.client = client;
  }

  @Override
  public void login(String username, String password) {

  }
}
