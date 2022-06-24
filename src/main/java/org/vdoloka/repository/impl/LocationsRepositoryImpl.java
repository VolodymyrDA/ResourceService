package org.vdoloka.repository.impl;

import org.vdoloka.entity.LocationEntity;
import org.vdoloka.repository.LocationsRepository;
import org.vdoloka.repository.mapper.LocationRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LocationsRepositoryImpl implements LocationsRepository {


    private final NamedParameterJdbcTemplate namedjdbcTemplate;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LocationsRepositoryImpl(NamedParameterJdbcTemplate namedjdbcTemplate, JdbcTemplate jdbcTemplate) {
        this.namedjdbcTemplate = namedjdbcTemplate;
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<LocationEntity> getLocations() {
        String sql = "SELECT id,name  FROM locations";
        return namedjdbcTemplate.query(sql, new LocationRowMapper());
    }
}