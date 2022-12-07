package org.vdoloka.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.vdoloka.entity.HubEntity;
import org.vdoloka.repository.impl.HubsRepositoryImpl;

import java.util.List;

@RestController
public class HubsResourcesRestController {
    private final HubsRepositoryImpl hubsRepository ;

    @Autowired
    public HubsResourcesRestController(HubsRepositoryImpl hubsRepository) {
        this.hubsRepository = hubsRepository;
    }


    @GetMapping("/hubResources/get")
    public List<HubEntity> getEntries(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                      @RequestParam(name = "itemPerPage", required = false, defaultValue = "10") int itemPerPage) {
        return hubsRepository.getResources(page, itemPerPage);
    }

    @PostMapping("/hubResources/add")
    public void supplementHubResources(HubEntity hubEntity) { hubsRepository.increaseResourceQuantityBySupplement(hubEntity);
    }
}