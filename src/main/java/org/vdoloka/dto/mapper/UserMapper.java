package org.vdoloka.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.vdoloka.dto.UserDTO;
import org.vdoloka.entity.User;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "role", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "date", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "sub", ignore = true)
    User toEntity(UserDTO userDTO);
}