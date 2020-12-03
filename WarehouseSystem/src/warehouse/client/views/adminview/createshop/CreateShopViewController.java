package warehouse.client.views.adminview.createshop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import warehouse.client.core.ViewHandler;
import warehouse.client.core.ViewModelFactory;
import warehouse.client.views.ViewController;

public class CreateShopViewController implements ViewController {
    @FXML private TextField cityTextField;
    @FXML private TextField streetTextField;
    @FXML private Button onCreateButton;

    private ViewHandler viewHandler;
    private CreateShopViewModel viewModel;

    @Override
    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) {
        this.viewHandler = viewHandler;
        viewModel = viewModelFactory.getCreateShopViewModel();
    }

    public void onLogout() {
    }

    public void onShopsOverview() {
    }
}
