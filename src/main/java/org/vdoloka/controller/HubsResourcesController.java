package org.vdoloka.controller;

import org.vdoloka.entity.HubEntity;
import org.vdoloka.repository.impl.HubsRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HubsResourcesController {
    private final HubsRepositoryImpl hubsRepository ;

    @Autowired
    public HubsResourcesController(HubsRepositoryImpl hubsRepository) {
        this.hubsRepository = hubsRepository;
    }

    @GetMapping("/hubResources")
    public String getMainPage() {
        return "resources";
    }

    @GetMapping("/hubResources/get")
    @ResponseBody
    public List<HubEntity> getEntries(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                      @RequestParam(name = "itemPerPage", required = false, defaultValue = "10") int itemPerPage) {
        return hubsRepository.getResources(page, itemPerPage);
    }

    @ResponseBody
    @PostMapping("/hubResources/add")
    public void supplementHubResources(HubEntity hubEntity) { hubsRepository.increaseResourceQuantityBySupplement(hubEntity);
    }
}