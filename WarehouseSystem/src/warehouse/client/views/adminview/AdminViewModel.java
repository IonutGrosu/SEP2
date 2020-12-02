package warehouse.client.views.adminview;

import warehouse.client.model.WarehouseModel;
import warehouse.shared.util.PropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AdminViewModel implements PropertyChangeSubject
{
  private WarehouseModel warehouseModel;
  private PropertyChangeSupport support;

  public AdminViewModel(WarehouseModel warehouseModel)
  {
    this.warehouseModel = warehouseModel;
  }

  @Override public void addPropertyListener(String eventName,
      PropertyChangeListener listener)
  {
    if(eventName == null)
    {
      support.addPropertyChangeListener(listener);
    } else {
      support.addPropertyChangeListener(eventName, listener);
    }
  }
}
