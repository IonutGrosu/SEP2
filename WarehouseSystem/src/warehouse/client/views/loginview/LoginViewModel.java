package warehouse.client.views.loginview;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import warehouse.client.model.WarehouseModel;
import warehouse.shared.transferObjects.User;
import warehouse.shared.util.PropertyChangeSubject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class  LoginViewModel
{
  private WarehouseModel warehouseModel;
  private PropertyChangeSupport support;

  public LoginViewModel(WarehouseModel warehouseModel)
  {
    this.warehouseModel = warehouseModel;
    support = new PropertyChangeSupport(this);
  }

  public User sendCredentials(String username, String password)
  {
    return warehouseModel.login(username, password);
  }
}
