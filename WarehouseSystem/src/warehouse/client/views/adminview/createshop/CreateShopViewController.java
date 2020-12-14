package warehouse.client.views.adminview.createshop;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import warehouse.client.core.ViewHandler;
import warehouse.client.core.ViewModelFactory;
import warehouse.client.views.ViewController;
import warehouse.shared.transferObjects.EventType;

import java.beans.PropertyChangeEvent;

public class CreateShopViewController implements ViewController {
    @FXML private Label actionResponseLabel;
    @FXML private Button goToUserOverviewResponseButton;
    @FXML private TextField streetTextField;
    @FXML private TextField cityTextField;
    @FXML private TextField zipCodeTextField;

    private ViewHandler viewHandler;
    private CreateShopViewModel viewModel;



    @Override
    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) {
        this.viewHandler = viewHandler;
        viewModel = viewModelFactory.getCreateShopViewModel();

        goToUserOverviewResponseButton.setVisible(false);
        actionResponseLabel.textProperty().bind(viewModel.getResponseLabelProperty());

        viewModel.addPropertyListener(EventType.SUCCESSFUL_SHOP_CREATION.toString(), this::shopCreateResponse);
        viewModel.addPropertyListener(EventType.UNSUCCESSFUL_SHOP_CREATION.toString(), this::shopCreateResponse);

        streetTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER))
                    onCreateButton();
            }
        });
    }

    @Override
    public void updateView() {

    }

    private void shopCreateResponse(PropertyChangeEvent event) {
        if (event.getPropertyName().equals(EventType.SUCCESSFUL_SHOP_CREATION.toString()))
            goToUserOverviewResponseButton.setVisible(true);
        else
            goToUserOverviewResponseButton.setVisible(false);
    }

    public void onLogout() {

    }

    public void onShopsOverview() {
        viewHandler.openAdminShopsOverviewView();
    }

    public void onCreateButton() {
        String imputedStreet = streetTextField.textProperty().getValue();
        String imputedCity = cityTextField.textProperty().getValue();
        String imputedZipCode = zipCodeTextField.textProperty().getValue();

        if (!imputedStreet.isEmpty() && !imputedCity.isEmpty() && !imputedZipCode.isEmpty()) {
            viewModel.createShop(imputedCity, imputedStreet, imputedZipCode);
        }
    }

    public void onUsersOverview() {
        viewHandler.openAdminUsersOverviewView();
    }
}
