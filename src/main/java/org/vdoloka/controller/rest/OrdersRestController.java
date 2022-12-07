package org.vdoloka.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.vdoloka.entity.OrderEntity;
import org.vdoloka.service.impl.OrdersServiceImpl;

import javax.validation.Valid;
import java.util.List;

@RestController
public class OrdersRestController {
    private final OrdersServiceImpl ordersService;

    @Autowired
    public OrdersRestController(OrdersServiceImpl ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping("/orders/get")
    public List<OrderEntity> getPageData(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                         @RequestParam(name = "itemPerPage", required = false, defaultValue = "10") int itemPerPage) {
        return ordersService.getOrders(page, itemPerPage);
    }

    @PostMapping("/orders/add")
    public void placeOrder(@Valid OrderEntity orderEntity) {
        ordersService.addOrder(orderEntity);
    }
}