package org.vdoloka.dto.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.vdoloka.dto.UserDTO;

import org.vdoloka.entity.User;

class UserMapperTest {

    private final UserMapper userMapper = UserMapper.INSTANCE;

    @Test
    void shouldMapUserDtoToUserEntity() {
        UserDTO userDto = new UserDTO();
        userDto.setUsername("testUser");
        userDto.setPassword("testPassword");
        userDto.setPhone("1234567890123");
        userDto.setDescription("testDescription");
        userDto.setLocationId(1);

        User user = userMapper.toEntity(userDto);

        assertThat(user.getUsername()).isEqualTo(userDto.getUsername());
        assertThat(user.getPassword()).isEqualTo(userDto.getPassword());
        assertThat(user.getPhone()).isEqualTo(userDto.getPhone());
        assertThat(user.getDescription()).isEqualTo(userDto.getDescription());
        assertThat(user.getLocationId()).isEqualTo(userDto.getLocationId());
    }
}