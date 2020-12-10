package warehouse.client.views.loginview;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import warehouse.client.core.ViewHandler;
import warehouse.client.core.ViewModelFactory;
import warehouse.client.views.ViewController;

import java.beans.PropertyChangeEvent;

public class LoginViewController implements ViewController {

    private LoginViewModel loginViewModel;
    private ViewHandler viewHandler;

    @FXML public TextField usernameId;
    @FXML public TextField passwordId;
    @FXML public Label errorLabelId;


    @Override
    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) {
        this.loginViewModel = viewModelFactory.getLoginViewModel();
        this.viewHandler = viewHandler;

        errorLabelId.textProperty().setValue("");
        errorLabelId.textProperty().bind(loginViewModel.getError());

        loginViewModel.addPropertyListener("loginResponse", this::openView);
        loginViewModel.addPropertyListener("MANAGER", this::openView);
        loginViewModel.addPropertyListener("EMPLOYEE", this::openView);
        loginViewModel.addPropertyListener("ADMIN", this::openView);


        usernameId.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER))
                    sendCredentials();
            }
        });
        passwordId.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER))
                    sendCredentials();
            }
        });
    }

    @Override
    public void updateView() {

    }

    private void openView(PropertyChangeEvent propertyChangeEvent)
    {

        switch (propertyChangeEvent.getPropertyName())
        {
            case "MANAGER" :
//                Platform.runLater(() -> {
//                    viewHandler.openViewPlaceholder();
//                });
                break;
            case "EMPLOYEE" :
//                Platform.runLater(() -> {
//                    viewHandler.openView2Placeholder();
//                });
                break;
            case "ADMIN" :
//                Platform.runLater(() -> {
//                    viewHandler.openView3Placeholder();
//                });
                break;
            case "loginResponse" :
//                Platform.runLater(() -> {
//                    viewHandler.openView4Placeholder();
//                });
                System.out.println("It works and here in the controller should"
                    + " call the next view");
                break;
        }
    }

    @FXML
    public void sendCredentials()
    {
        viewHandler.openAdminUsersOverviewView();//testing purposes, delete this when implementing the full login system
        if (usernameId != null && passwordId != null)
        loginViewModel.sendCredentials(usernameId.getText(), passwordId.getText());
    }
}
