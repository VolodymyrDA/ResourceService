package org.vdoloka.entity;

import lombok.Data;

@Data
public class HubEntity {
    private final int resourceId;
    private final int quantity;
    private final String resourceName;
}
