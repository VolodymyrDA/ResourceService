package org.vdoloka.repository.mapper;

import org.vdoloka.entity.HubEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HubRowMapper implements RowMapper<HubEntity> {
    @Override
    public HubEntity mapRow(ResultSet rs, int rowNum) throws SQLException {

        return new HubEntity(rs.getInt("resource_id"),
                rs.getInt("quantity"), rs.getString("name"));
    }
}
