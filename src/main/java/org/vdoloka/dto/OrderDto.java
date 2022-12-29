package org.vdoloka.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;

@Builder
@Data
public class OrderDto {
    private final int resourceId;
    @Max(value = 1000, message = "too many quantity (max 1000) ")
    private final int quantity;
    private int id;
    private int hubId;
    private String resourceName;
}
