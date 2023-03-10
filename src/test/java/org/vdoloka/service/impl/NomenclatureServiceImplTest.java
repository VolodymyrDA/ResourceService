package org.vdoloka.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.vdoloka.entity.Category;
import org.vdoloka.entity.Location;
import org.vdoloka.entity.Resource;
import org.vdoloka.entity.SubCategory;
import org.vdoloka.repository.CategoriesRepository;
import org.vdoloka.repository.LocationsRepository;
import org.vdoloka.repository.ResourcesRepository;
import org.vdoloka.repository.SubCategoriesRepository;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NomenclatureServiceImplTest {
    @Mock
    private ResourcesRepository resourcesRepository;
    @Mock
    private CategoriesRepository categoriesRepository;
    @Mock
    private SubCategoriesRepository subCategoriesRepository;
    @Mock
    private LocationsRepository locationsRepository;
    @InjectMocks
    private NomenclatureServiceImpl nomenclatureService;

    @Test
    void testGetResources() {
        int subcategoryId = 1;
        List<Resource> expectedResources = List.of(
                new Resource(1, "Resource 1", 1),
                new Resource(2, "Resource 2", 1)
        );
        when(resourcesRepository.findAllBySubcategoryId(subcategoryId)).thenReturn(expectedResources);

        List<Resource> actualResources = nomenclatureService.getResources(subcategoryId);

        assertThat(actualResources).isEqualTo(expectedResources);
    }

    @Test
    void testGetCategories() {
        List<Category> expectedCategories = List.of(
                new Category(1, "Category 1"),
                new Category(2, "Category 2")
        );
        when(categoriesRepository.findAll()).thenReturn(expectedCategories);

        List<Category> actualCategories = nomenclatureService.getCategories();

        assertThat(actualCategories).isEqualTo(expectedCategories);
    }

    @Test
    void testGetLocations() {
        List<Location> expectedLocations = List.of(
                new Location(1, "Location 1"),
                new Location(2, "Location 2")
        );
        when(locationsRepository.getLocations()).thenReturn(expectedLocations);

        List<Location> actualLocations = nomenclatureService.getLocations();

        assertThat(actualLocations).isEqualTo(expectedLocations);
    }

    @Test
    void testGetSubcategories() {
        long categoryId = 1L;
        List<SubCategory> expectedSubcategories = Arrays.asList(
                new SubCategory(1, "SubCategory 1", 1),
                new SubCategory(2, "SubCategory 2", 1)
        );
        when(subCategoriesRepository.findAllByCategoryId(categoryId)).thenReturn(expectedSubcategories);

        List<SubCategory> actualSubcategories = nomenclatureService.getSubcategories(categoryId);

        assertThat(actualSubcategories).isEqualTo(expectedSubcategories);
    }
}