package org.vdoloka.entity;

import lombok.Data;

import javax.validation.constraints.Max;

@Data
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
}