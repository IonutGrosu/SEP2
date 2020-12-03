package warehouse.client.views.adminview.shopsoverview;

import warehouse.client.model.WarehouseModel;
import warehouse.shared.util.PropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AdminShopsOverviewViewModel implements PropertyChangeSubject
{
  private WarehouseModel warehouseModel;
  private PropertyChangeSupport support;

  public AdminShopsOverviewViewModel(WarehouseModel warehouseModel)
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
