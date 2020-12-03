package warehouse.client.core;

import warehouse.client.views.adminview.usersoverview.AdminUsersOverviewViewModel;
import warehouse.client.views.loginview.LoginViewModel;

public class ViewModelFactory
{
  private ModelFactory modelFactory;
  private LoginViewModel loginViewModel;
  private AdminUsersOverviewViewModel adminUsersOverviewViewModel;

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

  public AdminUsersOverviewViewModel getAdminViewModel()
  {
    if (adminUsersOverviewViewModel == null)
      adminUsersOverviewViewModel = new AdminUsersOverviewViewModel(modelFactory.getWarehouseModel());
    return adminUsersOverviewViewModel;
  }
}
