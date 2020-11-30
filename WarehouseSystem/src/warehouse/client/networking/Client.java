package warehouse.client.networking;

import warehouse.shared.util.PropertyChangeSubject;

public interface Client extends PropertyChangeSubject
{
    void login(String username, String password);
}
