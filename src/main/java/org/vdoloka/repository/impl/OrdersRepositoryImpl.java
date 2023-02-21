package org.vdoloka.repository.impl;

import org.vdoloka.config.UserPrincipal;
import org.vdoloka.dto.HubOrderDTO;
import org.vdoloka.dto.HubResourcesDTO;
import org.vdoloka.dto.OrderDto;
import org.vdoloka.dto.OrderInfoDto;
import org.vdoloka.model.SortDirection;
import org.vdoloka.repository.OrdersRepository;
import org.vdoloka.repository.row_mapper.HubOrderRowMapper;
import org.vdoloka.repository.row_mapper.HubResourcesDTORowMapper;
import org.vdoloka.repository.row_mapper.OrderDTORowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class OrdersRepositoryImpl implements OrdersRepository {
    private final NamedParameterJdbcTemplate namedJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OrdersRepositoryImpl(NamedParameterJdbcTemplate namedJdbcTemplate, JdbcTemplate jdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getCurrentUserId() {
        return ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }

    @Override
    public void addOrder(OrderDto orderDto) {
        String sql = "INSERT INTO orders (resource_id, quantity ,user_id ) VALUES (:resourceId,:quantity,:userId)";
        namedJdbcTemplate.update(sql,
                Map.of("resourceId", orderDto.getResourceId(), "quantity", orderDto.getQuantity(),
                        "userId", getCurrentUserId()));
    }

    @Override
    public void confirmOrder(int orderId) {
        String sql = "UPDATE orders o " +
                "SET hub_id = '" + getCurrentUserId() + "'" +
                "WHERE o.id=" + orderId;
        jdbcTemplate.execute(sql);
    }

    @Override
    public List<OrderInfoDto> getOrders(int page, int size, String sort, SortDirection direction) {
        String sql = "SELECT resource_id,quantity,o.id,hub_id, r.name as \"r.name\"  FROM orders o " +
                "JOIN resources r on o.resource_id = r.id where o.user_id = " + getCurrentUserId() +
                " ORDER BY " + sort + " " + direction +
                " LIMIT " + size +
                " OFFSET " + (page - 1) * size;
        return namedJdbcTemplate.query(sql, new OrderDTORowMapper());
    }

    @Override
    public List<HubOrderDTO> getHubOrders(int page, int itemPerPage) {
        String sql = "SELECT o.id, l.name, u.description, u.phone, h.resource_id, h.quantity as \"h.quantity\", o.quantity as \"o.quantity\" " +
                "FROM orders o " +
                "JOIN hubs h on o.resource_id = h.resource_id and o.quantity < h.quantity " +
                "JOIN users u on o.user_id = u.id " +
                "JOIN locations l on u.location_id = l.id " +
                "WHERE h.hub_id = " + getCurrentUserId() + " and o.hub_id < 1 and o.user_id != " + getCurrentUserId() +
                " LIMIT " + itemPerPage +
                " OFFSET " + (page - 1) * itemPerPage;
        return namedJdbcTemplate.query(sql, new HubOrderRowMapper());
    }

    @Override
    public List<HubOrderDTO> getConfirmedOrders(int page, int itemPerPage) {
        String sql = "SELECT o.id, l.name, u.description, u.phone, h.resource_id, h.quantity as \"h.quantity\", o.quantity as \"o.quantity\" " +
                "FROM orders o " +
                "JOIN hubs h on o.hub_id = h.hub_id and o.resource_id=h.resource_id " +
                "JOIN users u on o.user_id = u.id " +
                "JOIN locations l on u.location_id = l.id " +
                "WHERE o.hub_id = " + getCurrentUserId() +
                " LIMIT " + itemPerPage +
                " OFFSET " + (page - 1) * itemPerPage;
        return namedJdbcTemplate.query(sql, new HubOrderRowMapper());
    }

    @Override
    public List<HubResourcesDTO> getLackResources(int page, int itemPerPage) {
        String sql = "SELECT o.resource_id, r.name, (SUM(quantity) - hq.quantity_h) as \"quantity\" " +
                "FROM orders o " +
                "         JOIN resources r on r.id = o.resource_id " +
                "         JOIN (SELECT h.resource_id, SUM(quantity) as \"quantity_h\" " +
                "               FROM hubs h\n" +
                "                        JOIN resources rs on rs.id = h.resource_id " +
                "               GROUP BY resource_id) hq\n" +
                "              ON hq.resource_id = o.resource_id and hub_id = 0 " +
                "GROUP BY o.resource_id, hq.quantity_h, r.name, quantity " +
                "HAVING (SUM(quantity) - hq.quantity_h) > 0 " +
                "ORDER BY resource_id" +
                " LIMIT " + itemPerPage +
                " OFFSET " + (page - 1) * itemPerPage;
        return namedJdbcTemplate.query(sql, new HubResourcesDTORowMapper());
    }

    @Override
    public List<HubResourcesDTO> getCountOrderingResources(int page, int itemPerPage) {
        String sql = "SELECT resource_id,r.name ,count(resource_id)  as \"quantity\" " +
                "FROM orders join resources r on orders.resource_id = r.id " +
                "GROUP BY  resource_id, r.name " +
                "ORDER BY  resource_id" +
                " LIMIT " + itemPerPage +
                " OFFSET " + (page - 1) * itemPerPage;
        return namedJdbcTemplate.query(sql, new HubResourcesDTORowMapper());
    }

    @Override
    public boolean isOrderExist(int orderId) {
        String sql = "SELECT count(*) FROM orders WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, orderId) > 0;
    }
}