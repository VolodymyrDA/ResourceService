package org.vdoloka.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.vdoloka.dto.HubResourcesDTO;
import org.vdoloka.service.ResourcesService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class HubResourcesController {
    private final ResourcesService resourcesService;

    @GetMapping("/hubResources/")
    public List<HubResourcesDTO> getEntries(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                            @RequestParam(name = "itemPerPage", required = false, defaultValue = "10") int itemPerPage) {
        return resourcesService.getResources(page, itemPerPage);
    }

    @PatchMapping("/hubResources/")
    public void supplementHubResources(HubResourcesDTO hubResourcesDTO) {
        resourcesService.increaseResourceQuantityBySupplement(hubResourcesDTO);
    }
}