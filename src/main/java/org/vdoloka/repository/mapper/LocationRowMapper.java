package org.vdoloka.repository.mapper;

import org.vdoloka.entity.LocationEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationRowMapper implements RowMapper<LocationEntity> {
    @Override
    public LocationEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        LocationEntity locationEntity = new LocationEntity(rs.getInt("id"),
                rs.getString("name"));
        return locationEntity;
    }
}