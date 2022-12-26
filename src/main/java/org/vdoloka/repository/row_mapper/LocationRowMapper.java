package org.vdoloka.repository.row_mapper;

import org.vdoloka.entity.Location;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationRowMapper implements RowMapper<Location> {
    @Override
    public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
        Location location = new Location(rs.getInt("id"),
                rs.getString("name"));
        return location;
    }
}