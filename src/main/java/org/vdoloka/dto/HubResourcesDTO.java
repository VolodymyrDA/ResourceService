package org.vdoloka.dto;

import lombok.Data;

@Data
public class HubResourcesDTO {
    private final int resourceId;
    private final int quantity;
    private final String resourceName;
}
