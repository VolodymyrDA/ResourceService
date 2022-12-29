package org.vdoloka.controller.rest;

import org.vdoloka.dto.ResourceDTO;
import org.vdoloka.dto.SubCategoryDTO;
import org.vdoloka.dto.mapper.ResourceMapper;
import org.vdoloka.dto.mapper.SubCategoryMapper;
import org.vdoloka.entity.Category;
import org.vdoloka.repository.CategoriesRepository;
import org.vdoloka.repository.ResourcesRepository;
import org.vdoloka.repository.SubCategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoriesController {
    private final ResourcesRepository resourcesRepository;
    private final CategoriesRepository categoriesRepository;
    private final SubCategoriesRepository subCategoriesRepository;

    @Autowired
    public CategoriesController(ResourcesRepository resourcesRepository, CategoriesRepository categoriesRepository, SubCategoriesRepository subCategoriesRepository) {
        this.resourcesRepository = resourcesRepository;
        this.categoriesRepository = categoriesRepository;
        this.subCategoriesRepository = subCategoriesRepository;
    }

    @GetMapping("/categories/")
    public Iterable<Category> getCategories() {
        return categoriesRepository.findAll();
    }

    @GetMapping(path = "/subcategories/{categoryId}")
    public List<SubCategoryDTO> getSubcategories(@PathVariable(value = "categoryId") int categoryId) {
        SubCategoryMapper.INSTANCE.map(subCategoriesRepository.findAllByCategoryId(categoryId));
        return SubCategoryMapper.INSTANCE.map(subCategoriesRepository.findAllByCategoryId(categoryId));
    }

    @GetMapping(path = "/resources/{subCategoryId}")
    public List<ResourceDTO> getResources(@PathVariable(value = "subCategoryId") int subCategoryId) {
        return ResourceMapper.INSTANCE.map(resourcesRepository.findAllBySubcategoryId(subCategoryId));
    }
}