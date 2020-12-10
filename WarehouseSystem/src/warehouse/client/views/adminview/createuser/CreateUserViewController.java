package warehouse.client.views.adminview.createuser;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import warehouse.client.core.ViewHandler;
import warehouse.client.core.ViewModelFactory;
import warehouse.client.views.ViewController;

import java.beans.PropertyChangeEvent;

public class CreateUserViewController implements ViewController
{
  private ViewHandler viewHandler;
  private CreateUserViewModel createUserViewModel;

  @FXML public TextField firstName;
  @FXML public TextField secondName;
  @FXML public TextField userName;
  @FXML public TextField password;
  @FXML public ComboBox role;
  @FXML public Button saveButton;
  @FXML public Button usersOverview;
  @FXML public Button shops;
  @FXML public Label notificationLabel;

  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    createUserViewModel = viewModelFactory.getCreateUserViewModel();

    // rethink place for filling comboBox
    role.getItems().add("MANAGER");
    role.getItems().add("EMPLOYEE");
    notificationLabel.textProperty().setValue("");
    notificationLabel.textProperty()
        .bind(createUserViewModel.getNotification());
    firstName.textProperty()
        .bindBidirectional(createUserViewModel.firstNameProperty());
    secondName.textProperty()
        .bindBidirectional(createUserViewModel.secondNameProperty());
    userName.textProperty()
        .bindBidirectional(createUserViewModel.userNameProperty());
    password.textProperty()
        .bindBidirectional(createUserViewModel.passwordProperty());
  }

  @Override
  public void updateView() {

  }

  public void onUsersOverview(ActionEvent actionEvent)
  {
    viewHandler.openAdminUsersOverviewView();
  }

  public void onSave(ActionEvent actionEvent)
  {
    // call the method that will be created inside the model for sending data down
    if (firstName.textProperty() != null
        && secondName.textFormatterProperty() != null
        && userName.textProperty() != null && password.textProperty() != null
        && role.getValue() != null)
    {
      createUserViewModel.forwardUserProperties((String)role.getValue());
    }
    else
    {
      // display message
    }
  }

  public void onShops(ActionEvent actionEvent)
  {
    viewHandler.openAdminShopsOverviewView();
  }

    public void onLogout() {
    }
}
