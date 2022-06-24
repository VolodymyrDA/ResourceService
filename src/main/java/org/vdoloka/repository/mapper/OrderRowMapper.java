package org.vdoloka.repository.mapper;

import org.vdoloka.entity.OrderEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<OrderEntity> {
    @Override
    public OrderEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrderEntity orderEntity = new OrderEntity(rs.getInt("resource_id"),
                rs.getInt("quantity"));
               orderEntity.setId(rs.getInt("id"));
        orderEntity.setHubId(rs.getInt("hub_id"));
        orderEntity.setResourceName(rs.getString("r.name"));
        return orderEntity;
    }
}