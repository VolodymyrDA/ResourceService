package org.vdoloka.DTO;

public class HubOrderDTO {
    private final int orderId;
    private final String locationName;
    private final String userDescription;
    private final int userPhone;
    private final int resourceID;
    private final int orderResourceQuantity;
    private final int hubResourceQuantity;

    public HubOrderDTO(int orderId, String locationName, String userDescription, int userPhone, int resourceID, int orderResourceQuantity, int hubResourceQuantity) {
        this.orderId = orderId;
        this.locationName = locationName;
        this.userDescription = userDescription;
        this.userPhone = userPhone;
        this.resourceID = resourceID;
        this.orderResourceQuantity = orderResourceQuantity;
        this.hubResourceQuantity = hubResourceQuantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getLocationName() {
        return locationName;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public int getUserPhone() {
        return userPhone;
    }

    public int getResourceID() {
        return resourceID;
    }

    public int getOrderResourceQuantity() {
        return orderResourceQuantity;
    }

    public int getHubResourceQuantity() {
        return hubResourceQuantity;
    }
}