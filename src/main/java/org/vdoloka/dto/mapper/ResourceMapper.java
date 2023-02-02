package org.vdoloka.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.vdoloka.dto.ResourceDTO;
import org.vdoloka.entity.Resource;

import java.util.List;

@Mapper
public interface ResourceMapper {
    ResourceMapper INSTANCE = Mappers.getMapper(ResourceMapper.class);

    ResourceDTO toDto(Resource resource);

    List<ResourceDTO> toDtoList(List<Resource> resources);
}