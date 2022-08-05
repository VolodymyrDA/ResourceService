package org.vdoloka.controller;

import org.vdoloka.entity.CategoryEntity;
import org.vdoloka.entity.ResourceEntity;
import org.vdoloka.entity.SubCategoryEntity;
import org.vdoloka.repository.impl.ResourcesRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoriesController {
    private final ResourcesRepositoryImpl resourcesRepository;

    @Autowired
    public CategoriesController(ResourcesRepositoryImpl resourcesRepository) {
        this.resourcesRepository = resourcesRepository;
    }

    @GetMapping("/categories/")
    public List<CategoryEntity> getCategories() {
        return resourcesRepository.getCategories();
    }


    @GetMapping(path = "/subcategories/{categoryId}")
    public List<SubCategoryEntity> getSubcategories(@PathVariable(value = "categoryId") int categoryId) {
        return resourcesRepository.getSubCategoriesByCategorie(categoryId);
    }

    @GetMapping(path = "/resources/{resourceId}")
    public List<ResourceEntity> getResources(@PathVariable(value = "resourceId") int resourceId) {
        return resourcesRepository.getResourcesBySubcategory(resourceId);
    }
}