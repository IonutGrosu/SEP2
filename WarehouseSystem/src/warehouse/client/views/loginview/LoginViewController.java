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
import warehouse.shared.transferObjects.User;

import java.beans.PropertyChangeEvent;

public class LoginViewController implements ViewController {

    private LoginViewModel loginViewModel;
    private ViewHandler viewHandler;

    @FXML public TextField usernameTextField;
    @FXML public TextField passwordTextField;
    @FXML public Label errorLabel;


    @Override
    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) {
        this.loginViewModel = viewModelFactory.getLoginViewModel();
        this.viewHandler = viewHandler;

        errorLabel.textProperty().setValue("");

        usernameTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER))
                    sendCredentials();
            }
        });
        passwordTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER))
                    sendCredentials();
            }
        });
    }

    @Override
    public void updateView() {}

    @FXML
    public void sendCredentials()
    {
        String imputedUsername = usernameTextField.textProperty().getValue();
        String imputedPassword = passwordTextField.textProperty().getValue();
        User userLoggedIn = null;
        if (!imputedUsername.isEmpty() && !imputedPassword.isEmpty())
            userLoggedIn = loginViewModel.sendCredentials(imputedUsername, imputedPassword);
        if (userLoggedIn == null) {
            errorLabel.textProperty().setValue("Wrong username or password");
        } else {
            String position = userLoggedIn.getPosition();
            switch(position) {
                case "ADMIN":
                    Platform.runLater(() -> {
                        viewHandler.openAdminUsersOverviewView();
                    });
                    break;
                case "EMPLOYEE":
                    Platform.runLater(() -> {
                        //viewHandler.openEmployeeView();
                        System.out.println("Employee view not created yet");
                    });
                    break;
                case "MANAGER":
                    Platform.runLater(() -> {
                        //viewHandler.openManagerView();
                        System.out.println("Manager view not created yet");
                    });
                    break;
            }
        }
    }
}
