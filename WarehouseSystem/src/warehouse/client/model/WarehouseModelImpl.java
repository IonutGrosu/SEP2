package warehouse.client.model;

import warehouse.client.networking.Client;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;

public class WarehouseModelImpl implements WarehouseModel
{
  private Client client;
  private PropertyChangeSupport support;
  private boolean temporaryBoolean;

  public WarehouseModelImpl(Client client)
  {
    this.client = client;
    support = new PropertyChangeSupport(this);
    client.addPropertyListener("loginResponse", this::loginResponse);
  }

  private void loginResponse(PropertyChangeEvent propertyChangeEvent)
  {
    temporaryBoolean = (boolean) propertyChangeEvent.getNewValue();
    support.firePropertyChange("loginResponse", null, temporaryBoolean);
  }

  @Override
  public void login(String username, String password) {
    try
    {
      client.login(username, password);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
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
