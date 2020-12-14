package warehouse.client.views.adminview.usersoverview;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import warehouse.client.core.ViewHandler;
import warehouse.client.core.ViewModelFactory;
import warehouse.client.views.ViewController;
import warehouse.shared.transferObjects.EventType;
import warehouse.shared.transferObjects.User;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

public class AdminUsersOverviewViewController implements ViewController
{
  private ViewHandler viewHandler;
  private AdminUsersOverviewViewModel adminUsersOverviewViewModel;

  @FXML public Button logout;
  @FXML public Button add;
  @FXML public Button edit;
  @FXML public Button remove;
  @FXML public ListView listEmployees;

  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    adminUsersOverviewViewModel = viewModelFactory.getAdminUsersOverviewViewModel();
    adminUsersOverviewViewModel.addPropertyListener("userCreated", this::addUser);
    adminUsersOverviewViewModel.addPropertyListener(EventType.ALL_USERS_LIST.toString(), this::updateUsersList);
  }

  private void updateUsersList(PropertyChangeEvent event) {
    ArrayList<User> allUsers = (ArrayList<User>) event.getNewValue();

    Platform.runLater(() -> {
      listEmployees.getItems().clear();
      listEmployees.getItems().addAll(allUsers);
    });

  }

  @Override
  public void updateView() {
    adminUsersOverviewViewModel.getAllUsers();
  }

  private void addUser(PropertyChangeEvent propertyChangeEvent)
  {
    listEmployees.getItems().add(propertyChangeEvent.getNewValue().toString());
    updateView();
  }

  public void onAdd(ActionEvent actionEvent)
  {
    viewHandler.openCreateUsersView();
  }

  public void onEdit(ActionEvent actionEvent)
  {

  }

  public void onRemove(ActionEvent actionEvent)
  {

  }

  public void onLogout(ActionEvent actionEvent)
  {

  }

  public void onShopsOverview() {
    viewHandler.openAdminShopsOverviewView();
  }
}
