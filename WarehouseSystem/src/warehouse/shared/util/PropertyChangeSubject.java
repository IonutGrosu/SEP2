package warehouse.shared.util;

import java.beans.PropertyChangeListener;

public interface PropertyChangeSubject
{
  void addPropertyListener(String eventName, PropertyChangeListener listener);
}
