package org.vdoloka.controller.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.vdoloka.dto.CategoryDTO;
import org.vdoloka.dto.ResourceDTO;
import org.vdoloka.dto.SubCategoryDTO;
import org.vdoloka.repository.CategoriesRepository;
import org.vdoloka.repository.ResourcesRepository;
import org.vdoloka.repository.SubCategoriesRepository;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoriesControllerTest {
    @Mock
    private CategoriesRepository categoriesRepository;
    @Mock
    private SubCategoriesRepository subCategoriesRepository;
    @Mock
    private ResourcesRepository resourcesRepository;
    @InjectMocks
    private CategoriesController categoriesController;

    @Test
    void testGetCategories() {
        List<CategoryDTO> expectedCategories = Collections.singletonList(new CategoryDTO());
        when(categoriesRepository.findAll()).thenReturn(Collections.emptyList());
        List<CategoryDTO> actualCategories = categoriesController.getCategories();
        assertThat(actualCategories).isEqualTo(expectedCategories);
    }

    @Test
    void testGetSubcategories() {
        int categoryId = 1;
        List<SubCategoryDTO> expectedSubCategories = Collections.singletonList(new SubCategoryDTO());
        when(subCategoriesRepository.findAllByCategoryId(categoryId)).thenReturn(Collections.emptyList());
        List<SubCategoryDTO> actualSubCategories = categoriesController.getSubcategories(categoryId);
        assertEquals(expectedSubCategories, actualSubCategories);
    }

    @Test
    void testGetResources() {
        int resourceId = 1;
        List<ResourceDTO> expectedResources = Collections.singletonList(new ResourceDTO());
        when(resourcesRepository.findAllBySubcategoryId(resourceId)).thenReturn(Collections.emptyList());
        List<ResourceDTO> actualResources = categoriesController.getResources(resourceId);
        assertEquals(expectedResources, actualResources);
    }
}