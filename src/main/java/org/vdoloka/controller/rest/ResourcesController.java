package org.vdoloka.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.vdoloka.dto.ResourceDTO;
import org.vdoloka.dto.mapper.ResourceMapper;
import org.vdoloka.service.NomenclatureService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ResourcesController {
    private final NomenclatureService nomenclatureService;

    @GetMapping(path = "/resources/{subcategoryId}")
    public List<ResourceDTO> getResources(@PathVariable int subcategoryId) {
        return ResourceMapper.INSTANCE.toDtoList(nomenclatureService.getResources(subcategoryId));
    }
}