package org.vdoloka.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.vdoloka.dto.HubOrderDTO;
import org.vdoloka.service.OrdersService;

import javax.validation.constraints.Digits;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class HubOrdersController {
    private final OrdersService ordersService;
    @GetMapping("/hubs/availableOrders")
    public List<HubOrderDTO> getOrdersPageData(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                               @RequestParam(name = "itemPerPage", required = false, defaultValue = "10") int itemPerPage) {
        return ordersService.getHubOrders(page, itemPerPage);
    }

    @GetMapping("/hubs/confirmedOrders")
    public List<HubOrderDTO> getConfirmedOrdersPageData(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                                        @RequestParam(name = "itemPerPage", required = false, defaultValue = "10") int itemPerPage) {
        return ordersService.getConfirmedOrders(page, itemPerPage);
    }

    @PutMapping(path = "/hubOrders/confirm/{orderId}")
    public void confirmOrderById(@PathVariable(value = "orderId") @Digits(message = "invalid data", integer = 10, fraction = 0) int orderId) {
        ordersService.confirmOrder(orderId);
    }
}