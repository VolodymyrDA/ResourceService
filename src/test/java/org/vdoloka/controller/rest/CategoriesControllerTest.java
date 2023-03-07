package org.vdoloka.controller.rest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.vdoloka.dto.CategoryDTO;
import org.vdoloka.entity.Category;
import org.vdoloka.service.NomenclatureService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class CategoriesControllerTest {
    @Mock
    private NomenclatureService nomenclatureService;

    @InjectMocks
    private CategoriesController categoriesController;
    @Test
    void shouldGetSubcategories() {
        List<Category> categories = List.of(new Category(1, "Category 1"),
                new Category(2, "Cubcategory 2"));

        when(nomenclatureService.getCategories()).thenReturn(categories);

        List<CategoryDTO> result = categoriesController.getCategories();

        assertThat(result)
                .isNotNull()
                .hasSize(categories.size())
                .extracting(CategoryDTO::getName)
                .containsExactly(categories.get(0).getName(), categories.get(1).getName());
    }
}