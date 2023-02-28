package org.vdoloka.controller.rest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.vdoloka.dto.CategoryDTO;
import org.vdoloka.entity.Category;
import org.vdoloka.repository.CategoriesRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class CategoriesControllerTest {

    private AutoCloseable closeable;
    @Mock
    private CategoriesRepository categoriesRepository;

    @InjectMocks
    private CategoriesController categoriesController;

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
        List<Category> categories = List.of(new Category(1, "Category 1"),
                new Category(2, "Cubcategory 2"));

        when(categoriesRepository.findAll()).thenReturn(categories);

        List<CategoryDTO> result = categoriesController.getCategories();

        assertThat(result)
                .isNotNull()
                .hasSize(categories.size())
                .extracting(CategoryDTO::getName)
                .containsExactly(categories.get(0).getName(), categories.get(1).getName());
    }
}