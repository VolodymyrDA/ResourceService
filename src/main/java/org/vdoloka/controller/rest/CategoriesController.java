package org.vdoloka.controller.rest;

import lombok.RequiredArgsConstructor;
import org.vdoloka.dto.CategoryDTO;
import org.vdoloka.dto.ResourceDTO;
import org.vdoloka.dto.SubCategoryDTO;
import org.vdoloka.dto.mapper.CategoryMapper;
import org.vdoloka.dto.mapper.ResourceMapper;
import org.vdoloka.dto.mapper.SubCategoryMapper;
import org.vdoloka.repository.CategoriesRepository;
import org.vdoloka.repository.ResourcesRepository;
import org.vdoloka.repository.SubCategoriesRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CategoriesController {
    private final ResourcesRepository resourcesRepository;
    private final CategoriesRepository categoriesRepository;
    private final SubCategoriesRepository subCategoriesRepository;

    @GetMapping("/categories/")
    public List<CategoryDTO> getCategories() {
        return CategoryMapper.INSTANCE.toDtoList(categoriesRepository.findAll());
    }

    @GetMapping(path = "/subcategories/{categoryId}")
    public List<SubCategoryDTO> getSubcategories(@PathVariable int categoryId) {
        return SubCategoryMapper.INSTANCE.toDtoList(subCategoriesRepository.findAllByCategoryId(categoryId));
    }

    @GetMapping(path = "/resources/{subCategoryId}")
    public List<ResourceDTO> getResources(@PathVariable int subCategoryId) {
        return ResourceMapper.INSTANCE.toDtoList(resourcesRepository.findAllBySubcategoryId(subCategoryId));
    }
}