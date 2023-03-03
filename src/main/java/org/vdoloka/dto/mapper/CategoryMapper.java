package org.vdoloka.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.vdoloka.dto.CategoryDTO;
import org.vdoloka.entity.Category;

import java.util.List;

@Mapper()
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    List<CategoryDTO> toDtoList(List<Category> categories);
}