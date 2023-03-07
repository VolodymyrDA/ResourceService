package org.vdoloka.controller.rest;

import lombok.RequiredArgsConstructor;
import org.vdoloka.dto.CategoryDTO;
import org.vdoloka.dto.mapper.CategoryMapper;
import org.springframework.web.bind.annotation.*;
import org.vdoloka.service.NomenclatureService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CategoriesController {
    private final NomenclatureService nomenclatureService;
    @GetMapping("/categories/")
    public List<CategoryDTO> getCategories() {
        return CategoryMapper.INSTANCE.toDtoList(nomenclatureService.getCategories());
    }
}