package org.vdoloka.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.vdoloka.dto.OrderDto;
import org.vdoloka.dto.OrderInfoDto;
import org.vdoloka.service.impl.OrdersServiceImpl;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrdersController {
    private final OrdersServiceImpl ordersService;

    @GetMapping("/orders/get")
    public List<OrderInfoDto> getPageData(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                          @RequestParam(name = "itemPerPage", required = false, defaultValue = "10") int itemPerPage) {
        return ordersService.getOrders(page, itemPerPage);
    }

    @PostMapping("/orders/add")
    public void addOrder(@Valid OrderDto orderDto) {
        ordersService.addOrder(orderDto);
    }
}