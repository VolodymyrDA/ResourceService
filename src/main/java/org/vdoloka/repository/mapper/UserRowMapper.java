package org.vdoloka.repository.mapper;

import org.vdoloka.entity.UserEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<UserEntity> {
    @Override
    public UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserEntity userEntity = new UserEntity(rs.getString("username"), rs.getString("password"),
                rs.getString("phone"), rs.getString("description"),
                rs.getInt("location_id"));
        userEntity.setId(rs.getInt("id"));
        userEntity.setRole(rs.getString("role_name"));
        return userEntity;
    }
}