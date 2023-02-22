package org.vdoloka.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.vdoloka.model.AnalyticsType;

@Controller
public class AnalyticsViewController {

    @GetMapping("/analytics")
    public String viewAnalytics(Model model, @RequestParam(name = "type", required = false, defaultValue = "RESOURCES_ON_HUBS") AnalyticsType type) {
        model.addAttribute("analyticsType", type);
        return "analytics";
    }
}
