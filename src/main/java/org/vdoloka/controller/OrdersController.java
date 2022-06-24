package org.vdoloka.controller;

import org.vdoloka.entity.OrderEntity;
import org.vdoloka.service.impl.OrdersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
public class OrdersController {
    private final OrdersServiceImpl ordersService;

    @Autowired
    public OrdersController(OrdersServiceImpl ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping("/orders")
    public String renderOrdersPage() {
        return "orders";
    }

    @GetMapping("/orders/get")
    @ResponseBody
    public List<OrderEntity> getPageData(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                         @RequestParam(name = "itemPerPage", required = false, defaultValue = "10") int itemPerPage) {
        return ordersService.getOrders(page, itemPerPage);
    }

    @ResponseBody
    @PostMapping("/orders/add")
    public void placeOrder(@Valid OrderEntity orderEntity) {
        ordersService.addOrder(orderEntity);
    }
}