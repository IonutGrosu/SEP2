package warehouse.client.views.adminview.shopsoverview;

import warehouse.client.model.WarehouseModel;
import warehouse.shared.transferObjects.EventType;
import warehouse.shared.transferObjects.Shop;
import warehouse.shared.util.PropertyChangeSubject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class AdminShopsOverviewViewModel implements PropertyChangeSubject {
    private WarehouseModel warehouseModel;
    private PropertyChangeSupport support;

    public AdminShopsOverviewViewModel(WarehouseModel warehouseModel) {
        this.warehouseModel = warehouseModel;
        support = new PropertyChangeSupport(this);

        this.warehouseModel.addPropertyListener(EventType.ALL_SHOPS_LIST.toString(), this::broadcastEvent);
        this.warehouseModel.addPropertyListener(EventType.SHOP_DELETED.toString(), this::onDeleteResponse);
        this.warehouseModel.addPropertyListener(EventType.SHOP_DELETE_ERROR.toString(), this::onDeleteResponse);
    }

    private void onDeleteResponse(PropertyChangeEvent propertyChangeEvent)
    {
        support.firePropertyChange(propertyChangeEvent);
    }

    private void broadcastEvent(PropertyChangeEvent event) {
        support.firePropertyChange(event);
    }

    @Override
    public void addPropertyListener(String eventName,
                                    PropertyChangeListener listener) {
        if (eventName == null) {
            support.addPropertyChangeListener(listener);
        } else {
            support.addPropertyChangeListener(eventName, listener);
        }
    }

    public void getAllShops() {
        warehouseModel.getAllShops();
    }

    public void removeShop(Shop shop)
    {
        warehouseModel.deleteShop(shop);
    }
}
