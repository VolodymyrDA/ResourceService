package org.vdoloka.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.vdoloka.dto.ResourceDTO;
import org.vdoloka.dto.mapper.ResourceMapper;
import org.vdoloka.repository.ResourcesRepository;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ResourcesController {
    private final ResourcesRepository resourcesRepository;

    @GetMapping(path = "/resources/{resourceId}")
    public List<ResourceDTO> getResources(@PathVariable int resourceId) {
        return ResourceMapper.INSTANCE.toDtoList(resourcesRepository.findAllBySubcategoryId(resourceId));
    }
}