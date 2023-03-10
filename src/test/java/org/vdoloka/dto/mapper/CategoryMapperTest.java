package org.vdoloka.dto.mapper;

import org.junit.jupiter.api.Test;
import org.vdoloka.dto.CategoryDTO;
import org.vdoloka.entity.Category;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CategoryMapperTest {

    @Test
    void testToDtoList() {
        // Given
        Category category1 = new Category();
        category1.setId(1);
        category1.setName("Category 1");

        Category category2 = new Category();
        category2.setId(2);
        category2.setName("Category 2");

        List<Category> categories = List.of(category1, category2);

        List<CategoryDTO> dtos = CategoryMapper.INSTANCE.toDtoList(categories);

        assertThat(dtos)
                .isNotNull()
                .hasSize(2);

        assertThat(dtos.get(0))
                .extracting(CategoryDTO::getId, CategoryDTO::getName)
                .containsExactly(1, "Category 1");

        assertThat(dtos.get(1))
                .extracting(CategoryDTO::getId, CategoryDTO::getName)
                .containsExactly(2, "Category 2");

    }
}