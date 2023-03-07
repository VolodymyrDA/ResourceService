package org.vdoloka.controller.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.vdoloka.dto.SubCategoryDTO;
import org.vdoloka.entity.SubCategory;
import org.vdoloka.service.NomenclatureService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SubCategoriesControllerTest {
    @Mock
    private NomenclatureService nomenclatureService;

    @InjectMocks
    private SubCategoriesController subCategoriesController;

    @Test
    void shouldGetSubcategories() {
        int categoryId = 1;
        List<SubCategory> subCategories = List.of(new SubCategory(1, "Subcategory 1", 1),
                new SubCategory(2, "Subcategory 2", 1));
        when(nomenclatureService.getSubcategories(categoryId)).thenReturn(subCategories);

        List<SubCategoryDTO> result = subCategoriesController.getSubcategories(categoryId);

        assertThat(result)
                .isNotNull()
                .hasSize(subCategories.size())
                .extracting(SubCategoryDTO::getName)
                .containsExactly(subCategories.get(0).getName(), subCategories.get(1).getName());
    }
}