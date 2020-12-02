package warehouse.client.core;

import warehouse.client.views.adminview.employees.AdminEmployeeViewModel;
import warehouse.client.views.loginview.LoginViewModel;

public class ViewModelFactory
{
  private ModelFactory modelFactory;
  private LoginViewModel loginViewModel;
  private AdminEmployeeViewModel adminEmployeeViewModel;

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

  public AdminEmployeeViewModel getAdminViewModel()
  {
    if (adminEmployeeViewModel == null)
      adminEmployeeViewModel = new AdminEmployeeViewModel(modelFactory.getWarehouseModel());
    return adminEmployeeViewModel;
  }
}
