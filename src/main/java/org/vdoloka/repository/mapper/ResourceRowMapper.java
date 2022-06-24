package org.vdoloka.repository.mapper;

import org.vdoloka.entity.ResourceEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResourceRowMapper implements RowMapper<ResourceEntity>{
    @Override
    public ResourceEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        ResourceEntity resourceEntity = new ResourceEntity(rs.getInt("id"),
                rs.getString("name"));
        return resourceEntity;
    }
}
