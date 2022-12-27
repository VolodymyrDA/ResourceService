package org.vdoloka.repository.impl;

import org.vdoloka.entity.Location;
import org.vdoloka.repository.LocationsRepository;
import org.vdoloka.repository.row_mapper.LocationRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LocationsRepositoryImpl implements LocationsRepository {

    private final NamedParameterJdbcTemplate namedjdbcTemplate;

    @Autowired
    public LocationsRepositoryImpl(NamedParameterJdbcTemplate namedjdbcTemplate) {
        this.namedjdbcTemplate = namedjdbcTemplate;
    }


    @Override
    public List<Location> getLocations() {
        String sql = "SELECT id,name  FROM locations";
        return namedjdbcTemplate.query(sql, new LocationRowMapper());
    }
}