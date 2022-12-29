package org.vdoloka.repository.row_mapper;

import org.vdoloka.dto.HubOrderDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HubOrderRowMapper implements RowMapper<HubOrderDTO> {
    @Override
    public HubOrderDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return HubOrderDTO.builder()
                .orderId(rs.getInt("id"))
                .locationName(rs.getString("name"))
                .userDescription(rs.getString("description"))
                .userPhone(rs.getInt("phone"))
                .resourceID(rs.getInt("resource_id"))
                .orderResourceQuantity(rs.getInt("o.quantity"))
                .hubResourceQuantity(rs.getInt("h.quantity"))
                .build();
    }
}