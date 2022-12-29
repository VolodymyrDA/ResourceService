package org.vdoloka.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.vdoloka.dto.ResourceDTO;
import org.vdoloka.dto.UserDTO;
import org.vdoloka.entity.Resource;
import org.vdoloka.entity.User;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDto(User user);
    User toEntity(UserDTO userDTO);
}