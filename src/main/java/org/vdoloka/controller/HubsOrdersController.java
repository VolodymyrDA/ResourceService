package org.vdoloka.controller;

import org.vdoloka.DTO.HubOrderDTO;
import org.vdoloka.service.impl.OrdersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Digits;
import java.util.List;

@Controller
public class HubsOrdersController {

    private final OrdersServiceImpl ordersService;

    @Autowired
    public HubsOrdersController(OrdersServiceImpl ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping("/hubsOrders")
    public String renderHubsOrdersPage() {
        return "hubsOrders";
    }

    @ResponseBody
    @GetMapping("/hubs/availableOrders")
    public List<HubOrderDTO> getOrdersPageData(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                               @RequestParam(name = "itemPerPage", required = false, defaultValue = "10") int itemPerPage) {
        return ordersService.getHubOrders(page, itemPerPage);
    }
    @ResponseBody
    @GetMapping("/hubs/confirmedOrders")
    public List<HubOrderDTO> getConfirmedOrdersPageData(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                        @RequestParam(name = "itemPerPage", required = false, defaultValue = "10") int itemPerPage) {
        return ordersService.getConfirmedOrders(page, itemPerPage);
    }
    @ResponseBody
    @RequestMapping(value = {"/hubOrders/confirm/{orderId}"}, method = RequestMethod.PUT)
    public void confirmOrderById(@PathVariable(value = "orderId") @Digits(message = "invalid data", integer = 10, fraction = 0) int orderId) {
        ordersService.confirmOrder(orderId);
    }
}