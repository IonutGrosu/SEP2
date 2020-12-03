package warehouse.client.core;

import warehouse.client.views.adminview.shopsoverview.AdminShopsOverviewViewModel;
import warehouse.client.views.adminview.usersoverview.AdminUsersOverviewViewModel;
import warehouse.client.views.loginview.LoginViewModel;

public class ViewModelFactory
{
  private ModelFactory modelFactory;
  private LoginViewModel loginViewModel;
  private AdminUsersOverviewViewModel adminUsersOverviewViewModel;
  private AdminShopsOverviewViewModel adminShopsOverviewViewModel;

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

  public AdminUsersOverviewViewModel getAdminUsersOverviewViewModel()
  {
    if (adminUsersOverviewViewModel == null)
      adminUsersOverviewViewModel = new AdminUsersOverviewViewModel(modelFactory.getWarehouseModel());
    return adminUsersOverviewViewModel;
  }
  public AdminShopsOverviewViewModel getAdminShopsOverviewViewModel()
  {
    if (adminShopsOverviewViewModel == null)
      adminShopsOverviewViewModel = new AdminShopsOverviewViewModel(modelFactory.getWarehouseModel());
    return adminShopsOverviewViewModel;
  }
}
