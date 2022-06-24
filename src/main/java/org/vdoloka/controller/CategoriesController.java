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

    @GetMapping("categories/get")
    public List<CategoryEntity> getCategories() {
        return resourcesRepository.getCategories();
    }

    @RequestMapping(value = {"subcategories/get/{categorieId}"}, method = RequestMethod.GET)
    public List<SubCategoryEntity> getSubcategories(@PathVariable(value = "categorieId") int categorieId) {
        return resourcesRepository.getSubCategoriesByCategorie(categorieId);
    }

    @RequestMapping(value = {"resources/get/{resourceId}"}, method = RequestMethod.GET)
    public List<ResourceEntity> getResources(@PathVariable(value = "resourceId") int resourceId) {
        return resourcesRepository.getResourcesBySubcategory(resourceId);
    }
}