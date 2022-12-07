package org.vdoloka.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrdersController {
    @GetMapping("/orders")
    public String renderOrdersPage() {
        return "orders";
    }
}