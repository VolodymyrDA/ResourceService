package org.vdoloka.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HubsOrdersController {
    @GetMapping("/hubsOrders")
    public String renderHubsOrdersPage() {
        return "hubsOrders";
    }
}