package warehouse.client.core;

import warehouse.client.views.placeholderview.LoginViewModel;

public class ViewModelFactory {
    private ModelFactory modelFactory;
    private LoginViewModel loginViewModel;

    public ViewModelFactory(ModelFactory modelFactory) {
        this.modelFactory = modelFactory;
    }

    public LoginViewModel getLoginViewModel() {
        if (loginViewModel == null) loginViewModel = new LoginViewModel(modelFactory.getWarehouseModel());
        return loginViewModel;
    }
}
