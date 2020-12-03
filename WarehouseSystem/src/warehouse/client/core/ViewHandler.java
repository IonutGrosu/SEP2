package warehouse.client.core;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import warehouse.client.views.adminview.shopsoverview.AdminShopsOverviewViewController;
import warehouse.client.views.adminview.usersoverview.AdminUsersOverviewViewController;
import warehouse.client.views.loginview.LoginViewController;

import java.io.IOException;

public class ViewHandler
{
  private ViewModelFactory viewModelFactory;
  private Stage stage;
  private Scene loginScene;
  private Scene adminScene;
//  private static Map<String, Scene> scenes;
//  private static ArrayList<String> names;

  // Flyweight for the scenes, rocket science

  public ViewHandler(Stage stage, ViewModelFactory viewModelFactory)
  {
    this.stage = stage;
    this.viewModelFactory = viewModelFactory;
//    scenes = new HashMap<>();
//    names = new ArrayList<>();
//    names.add("LoginView");
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

    if(adminScene == null)
    {
      Parent root = getRootByPath("../views/adminview/usersoverview/adminUsersOverview.fxml", loader);
      AdminUsersOverviewViewController controller = loader.getController();
      controller.init(this, viewModelFactory);
      adminScene = new Scene(root);
    }

    stage.setTitle("Welcome Admin");
    stage.setScene(adminScene);
  }
  public void openAdminShopsOverviewView()
  {
    FXMLLoader loader = new FXMLLoader();

    if(adminScene == null)
    {
      Parent root = getRootByPath("../views/adminview/shopsoverview/adminShopsOverview.fxml", loader);
     AdminShopsOverviewViewController controller = loader.getController();
      controller.init(this, viewModelFactory);
      adminScene = new Scene(root);
    }

    stage.setTitle("Welcome Admin");
    stage.setScene(adminScene);
  }

  public void start()
  {
    openLoginView();
    stage.show();
  }

  public void openViewPlaceholder()
  {
  }

  public void openView2Placeholder()
  {
  }

  public void openView3Placeholder()
  {
  }

  public void openView4Placeholder()
  {
  }
}
