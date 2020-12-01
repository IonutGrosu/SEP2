package warehouse.client.views.loginview;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import warehouse.client.model.WarehouseModel;

import java.beans.PropertyChangeEvent;

public class  LoginViewModel
{
  private WarehouseModel warehouseModel;
  private StringProperty error;

  public LoginViewModel(WarehouseModel warehouseModel)
  {
    this.warehouseModel = warehouseModel;
    error = new SimpleStringProperty();
    warehouseModel.addPropertyListener("loginResponse", this::loginResponse);
  }

  private void loginResponse(PropertyChangeEvent propertyChangeEvent)
  {

  }

  public StringProperty getError()
  {
    return error;
  }

  public void sendCredentials(String username, String password)
  {
    warehouseModel.login(username, password);
  }
}
