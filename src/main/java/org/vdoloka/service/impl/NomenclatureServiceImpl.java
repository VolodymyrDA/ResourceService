package org.vdoloka.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vdoloka.entity.Category;
import org.vdoloka.entity.Location;
import org.vdoloka.entity.Resource;
import org.vdoloka.entity.SubCategory;
import org.vdoloka.repository.CategoriesRepository;
import org.vdoloka.repository.LocationsRepository;
import org.vdoloka.repository.ResourcesRepository;
import org.vdoloka.repository.SubCategoriesRepository;
import org.vdoloka.service.NomenclatureService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NomenclatureServiceImpl implements NomenclatureService {
    private final ResourcesRepository resourcesRepository;
    private final CategoriesRepository categoriesRepository;
    private final SubCategoriesRepository subCategoriesRepository;
    private final LocationsRepository locationsRepository;

    @Override
    public List<Resource> getResources(int subcategoryId) {

        return resourcesRepository.findAllBySubcategoryId(subcategoryId);
    }

    @Override
    public List<Category> getCategories() {

        return categoriesRepository.findAll();
    }

    @Override
    public List<Location> getLocations() {

        return locationsRepository.getLocations();
    }

    @Override
    public List<SubCategory> getSubcategories(long categoryId) {

        return subCategoriesRepository.findAllByCategoryId(categoryId);
    }
}