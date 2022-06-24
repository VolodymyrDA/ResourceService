package org.vdoloka.entity;

public class HubEntity {
    private final int resourceId;
    private final int quantity;
    private String resourceName;

    public HubEntity(int resourceId, int quantity) {
        this.resourceId = resourceId;
        this.quantity = quantity;
    }

    public int getResourceId() {
        return resourceId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public String toString() {
        return "HubEntity{" +
                "resourceId=" + resourceId +
                ", quantity=" + quantity +
                ", resourceName='" + resourceName + '\'' +
                '}';
    }
}
