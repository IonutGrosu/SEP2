package warehouse.server.model;

import warehouse.shared.util.PropertyChangeSubject;

public interface ServerModel extends PropertyChangeSubject {
    boolean checkCredentials(String username, String password);
}
