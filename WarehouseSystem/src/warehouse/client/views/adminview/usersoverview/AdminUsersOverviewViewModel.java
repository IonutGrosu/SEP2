package warehouse.client.views.adminview.usersoverview;

import warehouse.client.model.WarehouseModel;
import warehouse.shared.transferObjects.EventType;
import warehouse.shared.util.PropertyChangeSubject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AdminUsersOverviewViewModel implements PropertyChangeSubject
{
  private WarehouseModel warehouseModel;
  private PropertyChangeSupport support;

  public AdminUsersOverviewViewModel(WarehouseModel warehouseModel)
  {
    this.warehouseModel = warehouseModel;
    support = new PropertyChangeSupport(this);
    warehouseModel.addPropertyListener("userCreated", this::broadcastEvent);
    warehouseModel.addPropertyListener(EventType.ALL_USERS_LIST.toString(), this::broadcastEvent);
  }

  private void broadcastEvent(PropertyChangeEvent propertyChangeEvent)
  {
    support.firePropertyChange(propertyChangeEvent);
  }

  @Override public void addPropertyListener(String eventName,
      PropertyChangeListener listener)
  {
    if (eventName == null)
    {
      support.addPropertyChangeListener(listener);
    }
    else
    {
      support.addPropertyChangeListener(eventName, listener);
    }
  }

    public void getAllUsers() {
    warehouseModel.getAllUsers();
    }
}
