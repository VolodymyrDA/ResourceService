package org.vdoloka.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.vdoloka.dto.SubCategoryDTO;
import org.vdoloka.entity.SubCategory;

import java.util.List;

@Mapper()
public interface SubCategoryMapper {
    SubCategoryMapper INSTANCE = Mappers.getMapper(SubCategoryMapper.class);
    SubCategoryDTO toDto(SubCategory subCategory);
    List<SubCategoryDTO> map(List<SubCategory> subCategories);
}
