package warehouse.client.views.loginview;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import warehouse.client.model.WarehouseModel;
import warehouse.shared.util.PropertyChangeSubject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class  LoginViewModel implements PropertyChangeSubject
{
  private WarehouseModel warehouseModel;
  private StringProperty error;
  private PropertyChangeSupport support;

  public LoginViewModel(WarehouseModel warehouseModel)
  {
    this.warehouseModel = warehouseModel;
    error = new SimpleStringProperty();
    support = new PropertyChangeSupport(this);
    warehouseModel.addPropertyListener("loginResponse", this::loginResponse);
    warehouseModel.addPropertyListener("MANAGER", this::loginResponse);
    warehouseModel.addPropertyListener("EMPLOYEE", this::loginResponse);
    warehouseModel.addPropertyListener("ADMIN", this::loginResponse);
    warehouseModel.addPropertyListener("errorResponse", this::errorResponse);
  }

  private void loginResponse(PropertyChangeEvent propertyChangeEvent)
  {
    support.firePropertyChange(propertyChangeEvent);
  }

  private void errorResponse(PropertyChangeEvent propertyChangeEvent)
  {
    Platform.runLater(()-> {
      error.setValue("Wrong username or password");
    });
  }

  public StringProperty getError()
  {
    return error;
  }

  public void sendCredentials(String username, String password)
  {
    warehouseModel.login(username, password);
  }

  @Override public void addPropertyListener(String eventName,
      PropertyChangeListener listener)
  {
    if(eventName == null)
    {
      support.addPropertyChangeListener(listener);
    } else {
      support.addPropertyChangeListener(eventName, listener);
    }
  }
}
