package org.vdoloka.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.vdoloka.config.UserPrincipal;
import org.vdoloka.entity.User;
import org.vdoloka.repository.UsersRepository;
import org.vdoloka.repository.row_mapper.UserRowMapper;

import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UsersRepositoryImpl implements UsersRepository {
    private final NamedParameterJdbcTemplate namedJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;

    private long getCurrentUserId() {
        return ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }

    @Override
    public long createUser(User user) {
        final int NEW_USER_ROLE_ID = 2;
        String sql = "INSERT INTO users (username, password ,description ,phone,date,location_id,active,role_id,sub)" +
                " VALUES (:userName,:password,:description,:phone,:date,:location,:active,:roleId,:sub)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedJdbcTemplate.update(sql, new MapSqlParameterSource(
                        Map.of("userName", user.getUsername(), "password", user.getPassword(),
                                "description", user.getDescription(), "phone", user.getPhone(),
                                "date", user.getDate(), "location", user.getLocationId(),
                                "active", user.getActive(), "roleId", NEW_USER_ROLE_ID, "sub", user.getSub()))
                , keyHolder, new String[]{"id"});
        return keyHolder.getKey().longValue();
    }

    @Override
    public void updateUser(User user) {
        String sql = "UPDATE users u SET ";
        if (user.getUsername() != null) {
            sql += "username = '" + user.getUsername() + "'";
        }
        if (!user.getPassword().isEmpty()) {
            sql += ",password = '" + user.getPassword() + "'";
        }
        if (user.getDescription() != null) {
            sql += ",description = '" + user.getDescription() + "'";
        }
        if (user.getPhone() != null) {
            sql += ",phone = '" + user.getPhone() + "'";
        }
        if (user.getLocationId() != 0) {
            sql += ",location_id = '" + user.getLocationId() + "'";
        }
        sql += " WHERE u.id=" + getCurrentUserId();
        jdbcTemplate.update(sql);
    }

    @Override
    public boolean isGoogleUserExist(String sub) {
        String countSql = "SELECT COUNT(*) FROM users WHERE sub = ?";
        return jdbcTemplate.queryForObject(countSql, Integer.class, sub) > 0;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        String countSql = "SELECT COUNT(*) FROM users WHERE username = ?";
        Integer count = jdbcTemplate.queryForObject(countSql, Integer.class, username);
        if (count == null || count == 0) {
            return Optional.empty();
        }
        String sql = "SELECT id,username,phone,date,description,location_id,password,active,r.role_name  FROM users u JOIN roles r on u.role_id = r.role_id " +
                "where username= '" + username + "'";

        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new UserRowMapper()));
    }

    @Override
    public Optional<User> findByUserID(long id) {
        String countSql = "SELECT COUNT(*) FROM users WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(countSql, Integer.class, id);
        if (count == null || count == 0) {
            return Optional.empty();
        }
        String selectSql = "SELECT id, username, phone, date, description, location_id, password, active, r.role_name FROM users u JOIN roles r on u.role_id = r.role_id WHERE id = ?";

        return Optional.ofNullable(jdbcTemplate.queryForObject(selectSql, new UserRowMapper()));
    }

    @Override
    public Optional<User> findByUserSub(String userSub) {
        String selectSql = "SELECT id, username, phone, date, description, location_id, password, active, sub, r.role_name " +
                "FROM users u JOIN roles r on u.role_id = r.role_id WHERE u.sub = '" + userSub + "'";
        return Optional.ofNullable(jdbcTemplate.queryForObject(selectSql, new UserRowMapper()));
    }
}