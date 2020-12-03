package warehouse.client.views.adminview.usersoverview;

import warehouse.client.model.WarehouseModel;
import warehouse.shared.util.PropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AdminUsersOverviewViewModel implements PropertyChangeSubject
{
  private WarehouseModel warehouseModel;
  private PropertyChangeSupport support;

  public AdminUsersOverviewViewModel(WarehouseModel warehouseModel)
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
