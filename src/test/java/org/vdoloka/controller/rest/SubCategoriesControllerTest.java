package org.vdoloka.controller.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.vdoloka.dto.SubCategoryDTO;
import org.vdoloka.entity.SubCategory;
import org.vdoloka.repository.SubCategoriesRepository;

class SubCategoriesControllerTest {

    private AutoCloseable closeable;
    @Mock
    private SubCategoriesRepository subCategoriesRepository;

    @InjectMocks
    private SubCategoriesController subCategoriesController;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }

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