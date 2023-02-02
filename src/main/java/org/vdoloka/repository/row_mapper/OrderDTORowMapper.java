package org.vdoloka.repository.row_mapper;

import org.vdoloka.dto.OrderInfoDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDTORowMapper implements RowMapper<OrderInfoDto> {
    @Override
    public OrderInfoDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return OrderInfoDto.builder()
                .resourceId(rs.getInt("resource_id"))
                .quantity(rs.getInt("quantity"))
                .id(rs.getInt("id"))
                .hubId(rs.getInt("hub_id"))
                .resourceName(rs.getString("r.name"))
                .build();
    }
}