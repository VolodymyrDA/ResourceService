package org.vdoloka.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.vdoloka.dto.HubResourcesDTO;
import org.vdoloka.service.impl.AnalyticsServiceImpl;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class AnalyticsController {
    private final AnalyticsServiceImpl analyticsService;

    @GetMapping("analytics/")
    public List<HubResourcesDTO> getAnalyticsEntries(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                                     @RequestParam(name = "itemPerPage", required = false, defaultValue = "10") int itemPerPage) {
        return analyticsService.getResourcesOnHubs(page, itemPerPage);
    }

    @GetMapping("analytics/lack/")
    public List<HubResourcesDTO> getAnalyticsLackEntries(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                                         @RequestParam(name = "itemPerPage", required = false, defaultValue = "10") int itemPerPage) {
        return analyticsService.getLackResources(page, itemPerPage);
    }

    @GetMapping("analytics/top/")
    public List<HubResourcesDTO> getAnalyticsTopEntries(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                                        @RequestParam(name = "itemPerPage", required = false, defaultValue = "10") int itemPerPage) {
        return analyticsService.getCountOrderingResources(page, itemPerPage);
    }
}