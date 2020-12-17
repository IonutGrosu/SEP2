package warehouse.client.core;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import warehouse.client.views.ViewController;
import warehouse.client.views.adminview.createshop.CreateShopViewController;
import warehouse.client.views.adminview.createuser.CreateUserViewController;
import warehouse.client.views.adminview.shopsoverview.AdminShopsOverviewViewController;
import warehouse.client.views.adminview.usersoverview.AdminUsersOverviewViewController;
import warehouse.client.views.loginview.LoginViewController;

import java.io.IOException;

public class ViewHandler
{
  private ViewModelFactory viewModelFactory;
  private Stage stage;
  private Scene loginScene;
  private Scene adminShopsOverviewScene;
  private Scene adminUsersOverviewScene;
  private Scene adminCreateShopScene;
  private Scene adminCreateUserScene;

  public ViewHandler(Stage stage, ViewModelFactory viewModelFactory)
  {
    this.stage = stage;
    this.viewModelFactory = viewModelFactory;
  }

  private Parent getRootByPath(String path, FXMLLoader loader)
  {
    loader.setLocation(getClass().getResource(path));
    Parent root = null;

    try{
      root = loader.load();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return root;
  }

  public void openLoginView()
  {
    FXMLLoader loader = new FXMLLoader();

    if(loginScene == null)
    {
      Parent root = getRootByPath("../views/loginview/Login.fxml", loader);
      LoginViewController controller = loader.getController();
      controller.init(this, viewModelFactory);
      loginScene = new Scene(root);
    }

    stage.setTitle("Login");
    stage.setScene(loginScene);
  }

  public void openAdminUsersOverviewView()
  {
    FXMLLoader loader = new FXMLLoader();
    AdminUsersOverviewViewController controller = null;

    if(adminUsersOverviewScene == null)
    {
      Parent root = getRootByPath("../views/adminview/usersoverview/adminUsersOverview.fxml", loader);
      controller = loader.getController();
      controller.init(this, viewModelFactory);
      adminUsersOverviewScene = new Scene(root);
    }

    if (controller != null)
      controller.updateView();

    stage.setTitle("Users overview");
    stage.setScene(adminUsersOverviewScene);
  }

  public void openCreateUsersView()
  {
    FXMLLoader loader = new FXMLLoader();
    CreateUserViewController controller = null;

    if(adminCreateUserScene == null)
    {
      Parent root = getRootByPath("../views/adminview/createuser/CreateUser.fxml", loader);
      controller = loader.getController();
      controller.init(this, viewModelFactory);
      adminCreateUserScene = new Scene(root);
    }

    if (controller != null)
      controller.updateView();

    stage.setTitle("Create user");
    stage.setScene(adminCreateUserScene);
  }

  public void openAdminShopsOverviewView()
  {
    FXMLLoader loader = new FXMLLoader();
    AdminShopsOverviewViewController controller = null;

    if(adminShopsOverviewScene == null)
    {
      Parent root = getRootByPath("../views/adminview/shopsoverview/adminShopsOverview.fxml", loader);
      controller = loader.getController();
      controller.init(this, viewModelFactory);
      adminShopsOverviewScene = new Scene(root);
    }

    if (controller != null)
      controller.updateView();

    stage.setTitle("Shops overview");
    stage.setScene(adminShopsOverviewScene);

  }

  public void openAdminCreateShopView() {
    FXMLLoader loader = new FXMLLoader();
    CreateShopViewController controller = null;

    if (adminCreateShopScene == null)
    {
      Parent root = getRootByPath("../views/adminview/createshop/CreateShop.fxml", loader);
      controller = loader.getController();
      controller.init(this, viewModelFactory);
      adminCreateShopScene = new Scene(root);
    }

    if (controller != null)
      controller.updateView();

    stage.setTitle("Create shop");
    stage.setScene(adminCreateShopScene);
  }

  public void start()
  {
    openLoginView();
    stage.show();
  }
}
