package warehouse.client.core;

import warehouse.client.views.adminview.createshop.CreateShopViewModel;
import warehouse.client.views.adminview.createuser.CreateUserViewModel;
import warehouse.client.views.adminview.shopsoverview.AdminShopsOverviewViewModel;
import warehouse.client.views.adminview.usersoverview.AdminUsersOverviewViewModel;
import warehouse.client.views.loginview.LoginViewModel;

public class ViewModelFactory
{
  private ModelFactory modelFactory;
  private LoginViewModel loginViewModel;
  private AdminUsersOverviewViewModel adminUsersOverviewViewModel;
  private AdminShopsOverviewViewModel adminShopsOverviewViewModel;
  private CreateShopViewModel createShopViewModel;
  private CreateUserViewModel createUserViewModel;

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
      adminUsersOverviewViewModel = new AdminUsersOverviewViewModel(
          modelFactory.getWarehouseModel());
    return adminUsersOverviewViewModel;
  }

  public AdminShopsOverviewViewModel getAdminShopsOverviewViewModel()
  {
    if (adminShopsOverviewViewModel == null)
      adminShopsOverviewViewModel = new AdminShopsOverviewViewModel(
          modelFactory.getWarehouseModel());
    return adminShopsOverviewViewModel;
  }

  public CreateShopViewModel getCreateShopViewModel()
  {
    if (createShopViewModel == null)
      createShopViewModel = new CreateShopViewModel(
          modelFactory.getWarehouseModel());
    return createShopViewModel;
  }

  public CreateUserViewModel getCreateUserViewModel()
  {
    if (createUserViewModel == null)
    {
      createUserViewModel = new CreateUserViewModel(
          modelFactory.getWarehouseModel());
    }
    return createUserViewModel;
  }
}
