package org.vdoloka.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/hubsOrders").setViewName("hubsOrders");
        registry.addViewController("/hubResources").setViewName("resources");
        registry.addViewController("/").setViewName("mainPage");
        registry.addViewController("/orders").setViewName("orders");
    }
}