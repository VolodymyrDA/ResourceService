package org.vdoloka.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.vdoloka.dto.HubResourcesDTO;
import org.vdoloka.model.AnalyticsType;
import org.vdoloka.service.ResourcesService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AnalyticsController {
    private final ResourcesService resourcesService;

    @GetMapping("analytics/")
    public List<HubResourcesDTO> getAnalytics(@RequestParam(name = "type", required = false, defaultValue = "RESOURCES_ON_HUBS") AnalyticsType type,
                                              @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                              @RequestParam(name = "itemPerPage", required = false, defaultValue = "10") int itemsPerPage) {
        return resourcesService.getAnalytics(type, page, itemsPerPage);
    }
}