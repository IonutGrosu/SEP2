package warehouse.client.views.adminview.shopsoverview;

import javafx.application.Platform;
import javafx.collections.ObservableList;
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
    adminShopsOverviewViewModel
        .addPropertyListener(EventType.ALL_SHOPS_LIST.toString(),
            this::updateListView);

    adminShopsOverviewViewModel.addPropertyListener(EventType.SHOP_DELETED.toString(), this::onDeleteUpdate);
    adminShopsOverviewViewModel.addPropertyListener(EventType.SHOP_DELETE_ERROR.toString(), this::onDeleteUpdate);
  }

  private void onDeleteUpdate(PropertyChangeEvent propertyChangeEvent)
  {
    if(EventType.SHOP_DELETED.toString().equals(propertyChangeEvent.getPropertyName()))
    {
      System.out.println("Shop deleted");
      updateView();
    }
    if(EventType.SHOP_DELETE_ERROR.toString().equals(propertyChangeEvent.getPropertyName()))
    {
      System.out.println("Error when shop tried to be deleted");
      updateView();
    }
  }

  @Override public void updateView()
  {
    adminShopsOverviewViewModel.getAllShops();
  }

  private void updateListView(PropertyChangeEvent event)
  {
    ArrayList<Shop> allShops = (ArrayList<Shop>) event.getNewValue();

    Platform.runLater(() -> {
      listShops.getItems().clear();
      listShops.getItems().addAll(allShops);
    });
  }

  public void onLogout(ActionEvent actionEvent)
  {
    // updated the list after an item has been removed
    adminShopsOverviewViewModel.getAllShops();
  }

  public void onAddShop(ActionEvent actionEvent)
  {
    viewHandler.openAdminCreateShopView();
  }

  public void onRemoveShop(ActionEvent actionEvent)
  {
    ObservableList selectedItem = listShops.getSelectionModel()
        .getSelectedItems();
    adminShopsOverviewViewModel.removeShop((Shop) selectedItem.get(0));
  }

  public void onUserOverview(ActionEvent actionEvent)
  {
    viewHandler.openAdminUsersOverviewView();
  }
}
