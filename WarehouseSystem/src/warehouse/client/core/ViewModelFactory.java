package warehouse.client.core;

import warehouse.client.views.adminview.AdminViewModel;
import warehouse.client.views.loginview.LoginViewModel;

public class ViewModelFactory
{
  private ModelFactory modelFactory;
  private LoginViewModel loginViewModel;
  private AdminViewModel adminViewModel;

  public ViewModelFactory(ModelFactory modelFactory)
  {
    this.modelFactory = modelFactory;
  }

  public LoginViewModel getLoginViewModel()
  {
    if (loginViewModel == null)
      loginViewModel = new LoginViewModel(modelFactory.getWarehouseModel());
    return loginViewModel;
  }

  public AdminViewModel getAdminViewModel()
  {
    if (adminViewModel == null)
      adminViewModel = new AdminViewModel(modelFactory.getWarehouseModel());
    return adminViewModel;
  }
}
