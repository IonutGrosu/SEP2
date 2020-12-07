package warehouse.client.views.adminview.createuser;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import warehouse.client.model.WarehouseModel;
import warehouse.shared.util.PropertyChangeSubject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CreateUserViewModel implements PropertyChangeSubject
{

  private WarehouseModel warehouseModel;
  private PropertyChangeSupport support;
  private StringProperty firstName, secondName, userName, password, role;
  private StringProperty notification;

  public CreateUserViewModel(WarehouseModel warehouseModel)
  {
    this.warehouseModel = warehouseModel;
    support = new PropertyChangeSupport(this);
    firstName = new SimpleStringProperty();
    secondName = new SimpleStringProperty();
    userName = new SimpleStringProperty();
    password = new SimpleStringProperty();
    role = new SimpleStringProperty();

    warehouseModel.addPropertyListener("userCreated", this::userCreation);
    warehouseModel.addPropertyListener("alreadyExistingUsername", this::displayError);
    warehouseModel.addPropertyListener("errorCreatingUserInDatabase", this::displayError);
    notification = new SimpleStringProperty();
  }

  private void displayError(PropertyChangeEvent propertyChangeEvent)
  {
    Platform.runLater(() -> {
      notification.setValue(propertyChangeEvent.getPropertyName());
    });
  }

  private void userCreation(PropertyChangeEvent propertyChangeEvent)
  {
    // if user is added successfully to the database
    Platform.runLater(()->{
      notification.setValue("The user has been added");
    });
//    support.firePropertyChange(propertyChangeEvent);
  }

  @Override public void addPropertyListener(String eventName,
      PropertyChangeListener listener)
  {
    if (eventName == null)
    {
      support.addPropertyChangeListener(listener);
    }
    else
    {
      support.addPropertyChangeListener(eventName, listener);
    }
  }

  public StringProperty getNotification()
  {
    return notification;
  }

  public StringProperty firstNameProperty()
  {
    return firstName;
  }

  public StringProperty secondNameProperty()
  {
    return secondName;
  }

  public StringProperty userNameProperty()
  {
    return userName;
  }

  public StringProperty passwordProperty()
  {
    return password;
  }

  public StringProperty roleProperty()
  {
    return role;
  }

  public void forwardUserProperties(String position)
  {
    warehouseModel
        .setUserProperties(firstName.getValue(), secondName.getValue(),
            userName.getValue(), password.getValue(), position);

  }
}
