package org.vdoloka.controller.rest;

import org.vdoloka.entity.CategoryEntity;
import org.vdoloka.entity.ResourceEntity;
import org.vdoloka.entity.SubCategoryEntity;
import org.vdoloka.repository.CategoriesRepository;
import org.vdoloka.repository.ResourcesRepository;
import org.vdoloka.repository.SubCategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Iterable<CategoryEntity> getCategories() {
        return categoriesRepository.findAll();
    }

    @GetMapping(path = "/subcategories/{categoryId}")
    public Iterable<SubCategoryEntity> getSubcategories(@PathVariable(value = "categoryId") int categoryId) {
                return subCategoriesRepository.findAllByCategoryId(categoryId);
    }

    @GetMapping(path = "/resources/{subCategoryId}")
    public Iterable<ResourceEntity> getResources(@PathVariable(value = "subCategoryId") int subCategoryId) {
        return resourcesRepository.findAllBySubcategoryId(subCategoryId);
    }
}