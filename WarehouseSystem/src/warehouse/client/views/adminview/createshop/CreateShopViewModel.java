package warehouse.client.views.adminview.createshop;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import warehouse.client.model.WarehouseModel;
import warehouse.shared.transferObjects.EventType;
import warehouse.shared.util.PropertyChangeSubject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CreateShopViewModel implements PropertyChangeSubject {

    private WarehouseModel model;
    private PropertyChangeSupport support;

    StringProperty responseLabelProperty;

    public CreateShopViewModel(WarehouseModel warehouseModel) {
        model = warehouseModel;
        support = new PropertyChangeSupport(this);

        responseLabelProperty = new SimpleStringProperty();

        model.addPropertyListener(EventType.SUCCESSFUL_SHOP_CREATION.toString(), this::createShopResponse);
        model.addPropertyListener(EventType.UNSUCCESSFUL_SHOP_CREATION.toString(), this::createShopResponse);
    }

    private void createShopResponse(PropertyChangeEvent event) {
        if (event.getPropertyName().equals(EventType.SUCCESSFUL_SHOP_CREATION.toString())) {
            Platform.runLater(()-> {
                responseLabelProperty.setValue("Shop created successfully");
            });
            support.firePropertyChange(event);
        } else if (event.getPropertyName().equals(EventType.UNSUCCESSFUL_SHOP_CREATION.toString())) {
            System.out.println("error creading shop");
            Platform.runLater(() -> {
                responseLabelProperty.setValue("Shop could not be created. Try a different city or street");
            });
            support.firePropertyChange(event);
        }
    }

    public void createShop(String imputedCity, String imputedStreet, String imputedZipCode) {
        model.createShop(imputedCity, imputedStreet, imputedZipCode);
    }

    public StringProperty getResponseLabelProperty() {
        return responseLabelProperty;
    }

    @Override
    public void addPropertyListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }
}
