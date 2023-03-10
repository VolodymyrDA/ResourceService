package org.vdoloka.dto.mapper;

import org.junit.jupiter.api.Test;
import org.vdoloka.dto.SubCategoryDTO;
import org.vdoloka.entity.SubCategory;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SubCategoryMapperTest {

    @Test
    void shouldMapToDto() {
        SubCategory subCategory = new SubCategory();
        subCategory.setId(1);
        subCategory.setName("SubCategory 1");
        subCategory.setCategoryId(1);

        SubCategoryDTO dto = SubCategoryMapper.INSTANCE.toDto(subCategory);

        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(1);
        assertThat(dto.getName()).isEqualTo("SubCategory 1");
    }

    @Test
    void shouldMapToDtoList() {
        SubCategory subCategory1 = new SubCategory();
        subCategory1.setId(1);
        subCategory1.setName("SubCategory 1");
        subCategory1.setCategoryId(1);

        SubCategory subCategory2 = new SubCategory();
        subCategory2.setId(2);
        subCategory2.setName("SubCategory 2");

        List<SubCategory> subCategories = Arrays.asList(subCategory1, subCategory2);

        List<SubCategoryDTO> dtos = SubCategoryMapper.INSTANCE.toDtoList(subCategories);

        assertThat(dtos)
                .isNotNull()
                .hasSize(2);

        assertThat(dtos.get(0))
                .extracting(SubCategoryDTO::getId, SubCategoryDTO::getName)
                .containsExactly(1, "SubCategory 1");

        assertThat(dtos.get(1))
                .extracting(SubCategoryDTO::getId, SubCategoryDTO::getName)
                .containsExactly(2, "SubCategory 2");
    }
}