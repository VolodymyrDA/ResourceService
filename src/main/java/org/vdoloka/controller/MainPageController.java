package org.vdoloka.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

    @GetMapping("/")
    public String viewMainPage() {
        return "mainpage";
    }
}