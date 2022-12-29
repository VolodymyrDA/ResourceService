package org.vdoloka.repository.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.vdoloka.entity.Location;
import org.vdoloka.repository.LocationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LocationsRepositoryImpl implements LocationsRepository {

    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    @Autowired
    public LocationsRepositoryImpl(NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }


    @Override
    public List<Location> getLocations() {
        String sql = "SELECT id,name  FROM locations";
        return namedJdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Location.class));
    }
}