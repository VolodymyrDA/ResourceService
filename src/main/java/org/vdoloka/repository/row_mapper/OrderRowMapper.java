package org.vdoloka.repository.row_mapper;

import org.vdoloka.dto.OrderDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<OrderDto> {
    @Override
    public OrderDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrderDto orderDto = new OrderDto(rs.getInt("resource_id"),
                rs.getInt("quantity"));
               orderDto.setId(rs.getInt("id"));
        orderDto.setHubId(rs.getInt("hub_id"));
        orderDto.setResourceName(rs.getString("r.name"));
        return orderDto;
    }
}