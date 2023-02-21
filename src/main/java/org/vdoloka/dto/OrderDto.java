package org.vdoloka.dto;

import lombok.*;

import javax.validation.constraints.Max;
@Data
@Builder
public class OrderDto {
    private final int resourceId;
    @Max(value = 1000, message = "too many quantity (max 1000) ")
    private final int quantity;
}
