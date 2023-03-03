package org.vdoloka.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.vdoloka.entity.Location;
import org.vdoloka.repository.LocationsRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LocationsRepositoryImpl implements LocationsRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Location> getLocations() {
        String sql = "SELECT id,name  FROM locations";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Location.class));
    }
}