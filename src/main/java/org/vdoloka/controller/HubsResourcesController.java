package org.vdoloka.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HubsResourcesController {
    @GetMapping("/hubResources")
    public String getMainPage() {
        return "resources";
    }
}