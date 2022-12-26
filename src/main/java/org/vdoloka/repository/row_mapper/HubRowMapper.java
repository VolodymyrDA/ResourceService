package org.vdoloka.repository.row_mapper;

import org.vdoloka.dto.HubResourcesDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HubRowMapper implements RowMapper<HubResourcesDTO> {
    @Override
    public HubResourcesDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

        return new HubResourcesDTO(rs.getInt("resource_id"),
                rs.getInt("quantity"), rs.getString("name"));
    }
}
