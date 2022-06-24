package org.vdoloka.entity;

import javax.validation.constraints.Max;

public class OrderEntity {
    private final int resourceId;
    @Max(value=1000,message = "too many quantity (max 1000) ")
    private final int quantity;
    private int id;
    private int hubId;
    private String resourceName;

    public OrderEntity(int resourceId, int quantity) {
        this.resourceId = resourceId;
        this.quantity = quantity;
    }

    public String getResourceName() {
        return resourceName;
    }

    public int getResourceId() {
        return resourceId;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getId() {
        return id;
    }

    public int getHubId() {
        return hubId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHubId(int hubId) {
        this.hubId = hubId;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
}