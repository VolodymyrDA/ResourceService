package org.vdoloka.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.vdoloka.dto.HubResourcesDTO;
import org.vdoloka.repository.impl.HubsRepositoryImpl;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class HubResourcesController {
    private final HubsRepositoryImpl hubsRepository;

    @GetMapping("/hubResources/get")
    public List<HubResourcesDTO> getEntries(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                            @RequestParam(name = "itemPerPage", required = false, defaultValue = "10") int itemPerPage) {
        return hubsRepository.getResources(page, itemPerPage);
    }

    @PostMapping("/hubResources/add")
    public void supplementHubResources(HubResourcesDTO hubResourcesDTO) {
        hubsRepository.increaseResourceQuantityBySupplement(hubResourcesDTO);
    }
}