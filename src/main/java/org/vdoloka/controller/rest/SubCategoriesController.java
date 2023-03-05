package org.vdoloka.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.vdoloka.dto.SubCategoryDTO;
import org.vdoloka.dto.mapper.SubCategoryMapper;
import org.vdoloka.service.NomenclatureService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class SubCategoriesController {
    private final NomenclatureService nomenclatureService;

    @GetMapping(path = "/subcategories/{categoryId}")
    public List<SubCategoryDTO> getSubcategories(@PathVariable int categoryId) {
        return SubCategoryMapper.INSTANCE.toDtoList(nomenclatureService.getSubcategories(categoryId));
    }
}