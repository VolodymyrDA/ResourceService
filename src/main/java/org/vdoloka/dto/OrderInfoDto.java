package org.vdoloka.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderInfoDto {
    private final int resourceId;
    private final int quantity;
    private int id;
    private int hubId;
    private String resourceName;
}