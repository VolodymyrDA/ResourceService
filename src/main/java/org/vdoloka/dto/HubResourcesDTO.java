package org.vdoloka.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class HubResourcesDTO {
    private final int resourceId;
    private final int quantity;
    private final String resourceName;
}
