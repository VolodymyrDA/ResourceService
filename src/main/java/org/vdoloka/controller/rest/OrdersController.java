package org.vdoloka.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.vdoloka.dto.OrderDto;
import org.vdoloka.dto.OrderInfoDto;
import org.vdoloka.model.SortDirection;
import org.vdoloka.service.impl.OrdersServiceImpl;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrdersController {
    private final OrdersServiceImpl ordersService;

    @GetMapping("/orders/")
    public List<OrderInfoDto> getOrders(        @RequestParam(defaultValue = "1") int page,
                                                @RequestParam(defaultValue = "10") int size,
                                                @RequestParam(defaultValue = "id") String sort,
                                                @RequestParam(defaultValue = "ASC") SortDirection direction) {
        return ordersService.getOrders(page, size,sort,direction);
    }

    @PostMapping("/orders/")
    public void addOrder(@Valid OrderDto orderDto) {
        ordersService.addOrder(orderDto);
    }
}