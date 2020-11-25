package warehouse.client;

import javafx.application.Application;
import javafx.stage.Stage;
import warehouse.client.core.ClientFactory;
import warehouse.client.core.ModelFactory;
import warehouse.client.core.ViewHandler;
import warehouse.client.core.ViewModelFactory;

public class WarehouseApp extends Application
{
  @Override public void start(Stage stage)
  {
    ClientFactory clientFactory = new ClientFactory();
    ModelFactory modelFactory = new ModelFactory(clientFactory);
    ViewModelFactory viewModelFactory = new ViewModelFactory(modelFactory);
    ViewHandler viewHandler = new ViewHandler(stage, viewModelFactory);
    viewHandler.start();
  }
}
