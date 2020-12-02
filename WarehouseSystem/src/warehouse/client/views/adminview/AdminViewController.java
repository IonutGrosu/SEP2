package warehouse.client.views.adminview;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import warehouse.client.core.ViewHandler;
import warehouse.client.core.ViewModelFactory;
import warehouse.client.views.ViewController;

public class AdminViewController implements ViewController
{
  private ViewHandler viewHandler;
  private AdminViewModel adminViewModel;

  @FXML public Button logout;
  @FXML public Button add;
  @FXML public Button edit;
  @FXML public Button remove;
  @FXML public ListView listEmployees;

  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    adminViewModel = viewModelFactory.getAdminViewModel();
  }

  public void onAdd(ActionEvent actionEvent)
  {

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

}
