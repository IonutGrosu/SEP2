package warehouse.client.views.loginview;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import warehouse.client.model.WarehouseModel;

public class  LoginViewModel
{
  private WarehouseModel warehouseModel;
  private StringProperty error;

  public LoginViewModel(WarehouseModel warehouseModel)
  {
    this.warehouseModel = warehouseModel;
    error = new SimpleStringProperty();
  }

  public StringProperty getError()
  {
    return error;
  }

  public void sendCredentials(String username, String password)
  {

  }
}
