package warehouse.client.views.placeholderview;

import warehouse.client.core.ViewHandler;
import warehouse.client.core.ViewModelFactory;
import warehouse.client.views.ViewController;

public class LoginViewController implements ViewController {
    private LoginViewModel loginViewModel;
    private ViewHandler viewHandler;

    @Override
    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) {
        this.loginViewModel = viewModelFactory.getLoginViewModel();

        this.viewHandler = viewHandler;
    }
}
