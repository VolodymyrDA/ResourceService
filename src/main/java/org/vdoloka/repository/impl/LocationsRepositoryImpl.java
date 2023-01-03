package org.vdoloka.repository.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.vdoloka.entity.Location;
import org.vdoloka.repository.LocationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LocationsRepositoryImpl implements LocationsRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LocationsRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Location> getLocations() {
        String sql = "SELECT id,name  FROM locations";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Location.class));
    }
}