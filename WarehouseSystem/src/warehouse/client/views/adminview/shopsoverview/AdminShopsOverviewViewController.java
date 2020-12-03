package warehouse.client.views.adminview.shopsoverview;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import warehouse.client.core.ViewHandler;
import warehouse.client.core.ViewModelFactory;
import warehouse.client.views.ViewController;

public class AdminShopsOverviewViewController implements ViewController
{
  private ViewHandler viewHandler;
  private AdminShopsOverviewViewModel adminShopsOverviewViewModel;

  @FXML public Button logout;
  @FXML public Button removeShop;
  @FXML public Button addShop;
  @FXML public Button goToUserOverview;
  @FXML public ListView listShops;

  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    adminShopsOverviewViewModel = viewModelFactory.getAdminShopsOverviewViewModel();
  }
  public void onUserOverview(ActionEvent actionEvent)
  {
  viewHandler.openAdminShopsOverviewView();
  }
  public void onLogout(ActionEvent actionEvent)
  {

  }
  public void onAddShop(ActionEvent actionEvent)
  {

  }
  public void onRemoveShop(ActionEvent actionEvent)
  {

  }
}
