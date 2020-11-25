package warehouse.server.model;

import warehouse.persistence.PlaceholderDAO;

public class ServerModelImpl implements ServerModel
{
  private PlaceholderDAO placeholderDAO;

  public ServerModelImpl(PlaceholderDAO placeholderDAO)
  {
    this.placeholderDAO = placeholderDAO;
  }

}
