package org.vdoloka.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.vdoloka.entity.HubEntity;
import org.vdoloka.service.impl.AnalyticsServiceImpl;

import java.util.List;


@RestController
public class AnalyticsRestController {
    private final AnalyticsServiceImpl analitycsService;

    @Autowired
    public AnalyticsRestController(AnalyticsServiceImpl analyticsService) {
        this.analitycsService = analyticsService;
    }


    @GetMapping("analytics/")
    public List<HubEntity> getAnalyticsEntries(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                               @RequestParam(name = "itemPerPage", required = false, defaultValue = "10") int itemPerPage) {
        return analitycsService.getResourcesOnHubs(page, itemPerPage);
    }

    @GetMapping("analytics/lack/")
    public List<HubEntity> getAnalyticsLackEntries(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                                   @RequestParam(name = "itemPerPage", required = false, defaultValue = "10") int itemPerPage) {
        return analitycsService.getLackResources(page, itemPerPage);
    }

    @GetMapping("analytics/top/")
    public List<HubEntity> getAnalyticsTopEntries(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                                  @RequestParam(name = "itemPerPage", required = false, defaultValue = "10") int itemPerPage) {
        return analitycsService.getCountOrderingResources(page, itemPerPage);
    }
}