package warehouse.client.core;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import warehouse.client.views.loginview.LoginViewController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ViewHandler
{
  private ViewModelFactory viewModelFactory;
  private Stage stage;
  private Scene loginScene;
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
