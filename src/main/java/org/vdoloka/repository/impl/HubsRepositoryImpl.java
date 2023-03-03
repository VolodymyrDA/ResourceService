package org.vdoloka.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.vdoloka.config.UserPrincipal;
import org.vdoloka.dto.HubResourcesDTO;
import org.vdoloka.repository.row_mapper.HubResourcesDTORowMapper;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class HubsRepositoryImpl implements org.vdoloka.repository.HubsRepository {

    private final NamedParameterJdbcTemplate namedJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;

    private long getCurrentUserId() {
        return ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }

    @Override
    public void reduceResourceQuantityByOrder(int orderId) {
        String sql = "UPDATE hubs h " + "SET quantity = (h.quantity - o.quantity) " + "from orders o " +
                "WHERE h.resource_id = o.resource_id " + "AND o.id = " + orderId + " AND h.hub_id =" + getCurrentUserId();
        jdbcTemplate.execute(sql);
    }

    @Override
    public void increaseResourceQuantityBySupplement(HubResourcesDTO hubResourcesDTO) {
        String sql = "select exists(select hub_id from hubs where hub_id=" + getCurrentUserId() + " and resource_id=" + hubResourcesDTO.getResourceId() + ")";
        if (Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, boolean.class))) {
            sql = "UPDATE hubs " + "SET  hub_id =" + getCurrentUserId() + ",resource_id=" + hubResourcesDTO.getResourceId()
                    + ",quantity=quantity+" + hubResourcesDTO.getQuantity() + " WHERE hub_id=" + getCurrentUserId() + " and resource_id=" + hubResourcesDTO.getResourceId();
        } else {
            sql = "INSERT INTO hubs (resource_id, quantity, hub_id) " + "VALUES (" + hubResourcesDTO.getResourceId() + "," + hubResourcesDTO.getQuantity() + "," + getCurrentUserId() + ")";
        }
        jdbcTemplate.execute(sql);
    }

    @Override
    public List<HubResourcesDTO> getAllResources(int page, int itemPerPage) {
        String sql = "SELECT resource_id,r.name, SUM(quantity) as \"quantity\" " + "FROM hubs h " + "JOIN resources r on r.id = h.resource_id "
                + "GROUP BY resource_id,r.name " + "ORDER BY resource_id" + " LIMIT " + itemPerPage + " OFFSET " + (page - 1) * itemPerPage;
        return namedJdbcTemplate.query(sql, new HubResourcesDTORowMapper());
    }

    @Override
    public List<HubResourcesDTO> getResources(int page, int itemPerPage) {
        String sql = "SELECT resource_id,r.name, quantity " + "FROM hubs h " + "JOIN resources r on r.id = h.resource_id "
                + "WHERE hub_id = " + getCurrentUserId() + " ORDER BY resource_id" + " LIMIT " + itemPerPage + " OFFSET " + (page - 1) * itemPerPage;
        return namedJdbcTemplate.query(sql, new HubResourcesDTORowMapper());
    }
}