package warehouse.client.views.adminview.shopsoverview;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import warehouse.client.core.ViewHandler;
import warehouse.client.core.ViewModelFactory;
import warehouse.client.views.ViewController;
import warehouse.shared.transferObjects.EventType;
import warehouse.shared.transferObjects.Shop;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

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
    adminShopsOverviewViewModel = viewModelFactory
        .getAdminShopsOverviewViewModel();
    adminShopsOverviewViewModel.addPropertyListener(EventType.ALL_SHOPS_LIST.toString(), this::updateListView);
    System.out.println("controller initialised and asking for the list of shops");
  }

  @Override
  public void updateView() {
    adminShopsOverviewViewModel.getAllShops();
  }

  private void updateListView(PropertyChangeEvent event) {
    ArrayList<Shop> allShops = (ArrayList<Shop>) event.getNewValue();
    System.out.println("controller received the list of shops and is populating the listview");
    Platform.runLater(() -> {
      listShops.getItems().clear();
      listShops.getItems().addAll(allShops);
    });
  }


  public void onLogout(ActionEvent actionEvent)
  {

  }

  public void onAddShop(ActionEvent actionEvent)
  {
    viewHandler.openAdminCreateShopView();
  }

  public void onRemoveShop(ActionEvent actionEvent)
  {
    adminShopsOverviewViewModel.getAllShops();
  }

  public void onUserOverview(ActionEvent actionEvent) {
    viewHandler.openAdminUsersOverviewView();
  }
}
