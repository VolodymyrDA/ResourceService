package org.vdoloka.repository.row_mapper;

import org.vdoloka.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return User.builder()
                .username(rs.getString("username"))
                .password(rs.getString("password"))
                .phone(rs.getString("phone"))
                .description(rs.getString("description"))
                .locationId(rs.getInt("location_id"))
                .date(rs.getTimestamp("date").toLocalDateTime())
                .active(rs.getBoolean("active"))
                .role(rs.getString("role_name"))
                .id(rs.getInt("id"))
                .build();
    }
}