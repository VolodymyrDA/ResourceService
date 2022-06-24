package org.vdoloka.repository.impl;

import org.vdoloka.config.UserPrincipal;
import org.vdoloka.entity.HubEntity;
import org.vdoloka.repository.HubsRepository;
import org.vdoloka.repository.mapper.HubRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HubsRepositoryImpl implements HubsRepository {

    private final NamedParameterJdbcTemplate namedjdbcTemplate;
    private final JdbcTemplate jdbcTemplate;

    public int getCurrentUserId() {
        return ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }

    @Autowired
    public HubsRepositoryImpl(NamedParameterJdbcTemplate namedjdbcTemplate, JdbcTemplate jdbcTemplate) {
        this.namedjdbcTemplate = namedjdbcTemplate;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void reduceResourceQuantityByOrder(int orderId) {
        String sql = "UPDATE hubs h " + "SET quantity = (h.quantity - o.quantity) " + "from orders o " +
                "WHERE h.resource_id = o.resource_id " + "AND o.id = " + orderId + " AND h.hub_id =" + getCurrentUserId();
        jdbcTemplate.execute(sql);
    }

    public void increaseResourceQuantityBySupplement(HubEntity hubEntity) {

        String sql = "select exists(select hub_id from hubs where hub_id=" + getCurrentUserId() + " and resource_id=" + hubEntity.getResourceId() + ")";
        if (Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, boolean.class))) {
            sql = "UPDATE hubs " + "SET  hub_id =" + getCurrentUserId() + ",resource_id=" + hubEntity.getResourceId()
                    + ",quantity=quantity+" + hubEntity.getQuantity() + " WHERE hub_id=" + getCurrentUserId() + " and resource_id=" + hubEntity.getResourceId();
        } else {
            sql = "INSERT INTO hubs (resource_id, quantity, hub_id) " + "VALUES (" + hubEntity.getResourceId() + "," + hubEntity.getQuantity() + "," + getCurrentUserId() + ")";
        }
        jdbcTemplate.execute(sql);
    }

    @Override
    public List<HubEntity> getAllResources(int page, int itemPerPage) {
        String sql = "SELECT resource_id,r.name, SUM(quantity) as \"quantity\" " + "FROM hubs h " + "JOIN resources r on r.id = h.resource_id "
                + "GROUP BY resource_id,r.name " + "ORDER BY resource_id" + " LIMIT " + itemPerPage + " OFFSET " + (page - 1) * itemPerPage;
        return namedjdbcTemplate.query(sql, new HubRowMapper());
    }

    @Override
    public List<HubEntity> getResources(int page, int itemPerPage) {
        String sql = "SELECT resource_id,r.name, quantity " + "FROM hubs h " + "JOIN resources r on r.id = h.resource_id "
                + "WHERE hub_id = " + getCurrentUserId() + " ORDER BY resource_id" + " LIMIT " + itemPerPage + " OFFSET " + (page - 1) * itemPerPage;
        return namedjdbcTemplate.query(sql, new HubRowMapper());
    }
}