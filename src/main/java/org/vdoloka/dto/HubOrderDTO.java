package org.vdoloka.dto;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class HubOrderDTO {
    private final int orderId;
    private final String locationName;
    private final String userDescription;
    private final int userPhone;
    private final int resourceID;
    private final int orderResourceQuantity;
    private final int hubResourceQuantity;
}