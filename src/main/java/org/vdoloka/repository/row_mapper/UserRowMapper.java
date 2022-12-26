package org.vdoloka.repository.row_mapper;

import org.vdoloka.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User(rs.getString("username"), rs.getString("password"),
                rs.getString("phone"), rs.getString("description"),
                rs.getInt("location_id"));
        user.setId(rs.getInt("id"));
        user.setRole(rs.getString("role_name"));
        return user;
    }
}