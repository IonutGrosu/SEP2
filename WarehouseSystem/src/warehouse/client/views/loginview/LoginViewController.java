package warehouse.client.views.loginview;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import warehouse.client.core.ViewHandler;
import warehouse.client.core.ViewModelFactory;
import warehouse.client.views.ViewController;

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

    @FXML
    public void sendCredentials()
    {
        loginViewModel.sendCredentials(usernameId.getText(), passwordId.getText());
    }
}