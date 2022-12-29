package org.vdoloka.repository.row_mapper;

import org.vdoloka.dto.HubResourcesDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HubResourcesDTORowMapper implements RowMapper<HubResourcesDTO> {
    @Override
    public HubResourcesDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

        return HubResourcesDTO.builder()
                .resourceId(rs.getInt("resource_id"))
                .quantity(rs.getInt("quantity"))
                .resourceName(rs.getString("name"))
                .build();
    }
}