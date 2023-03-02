package org.vdoloka.controller.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.vdoloka.dto.SubCategoryDTO;
import org.vdoloka.entity.SubCategory;
import org.vdoloka.repository.SubCategoriesRepository;

@ExtendWith(MockitoExtension.class)
class SubCategoriesControllerTest {
    @Mock
    private SubCategoriesRepository subCategoriesRepository;

    @InjectMocks
    private SubCategoriesController subCategoriesController;

    @Test
    void shouldGetSubcategories() {
        int categoryId = 1;
        List<SubCategory> subCategories = List.of(new SubCategory(1, "Subcategory 1", 1),
                new SubCategory(2, "Subcategory 2", 1));
        when(subCategoriesRepository.findAllByCategoryId(categoryId)).thenReturn(subCategories);

        List<SubCategoryDTO> result = subCategoriesController.getSubcategories(categoryId);

        assertThat(result)
                .isNotNull()
                .hasSize(subCategories.size())
                .extracting(SubCategoryDTO::getName)
                .containsExactly(subCategories.get(0).getName(), subCategories.get(1).getName());
    }
}