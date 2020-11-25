package warehouse.client.core;

import javafx.stage.Stage;

public class ViewHandler
{
  private ViewModelFactory viewModelFactory;
  private Stage stage;

  // Flyweight for the scenes, rocket science

  public ViewHandler(Stage stage, ViewModelFactory viewModelFactory)
  {
    this.stage = stage;
    this.viewModelFactory = viewModelFactory;
  }

  public void start()
  {
  }
}
