package warehouse.client.views;

import warehouse.client.core.ViewHandler;
import warehouse.client.core.ViewModelFactory;

public interface ViewController {
    void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory);
    void updateView();
}
