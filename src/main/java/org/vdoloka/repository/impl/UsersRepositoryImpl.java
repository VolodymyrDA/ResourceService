package org.vdoloka.repository.impl;

import lombok.RequiredArgsConstructor;
import org.vdoloka.config.UserPrincipal;
import org.vdoloka.entity.User;
import org.vdoloka.repository.UsersRepository;
import org.vdoloka.repository.row_mapper.UserRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UsersRepositoryImpl implements UsersRepository {
    private final NamedParameterJdbcTemplate namedJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;

    private int getCurrentUserId() {
        return ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }

    @Override
    public void addUser(User user) {
        final int newUserRoleId = 2;
        String sql = "INSERT INTO users (username, password ,description ,phone,date,location_id,active,role_id)" +
                " VALUES (:userName,:password,:description,:phone,:date,:location,:active,:roleId)";
        namedJdbcTemplate.update(sql,
                Map.of("userName", user.getUsername(), "password", user.getPassword(),
                        "description", user.getDescription(), "phone", user.getPhone(),
                        "date", user.getDate(), "location", user.getLocationId(),
                        "active", user.getActive(), "roleId", newUserRoleId)
        );
    }

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
    public Optional<User> findByUserID(int id) {
        String countSql = "SELECT COUNT(*) FROM users WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(countSql, Integer.class, id);
        if (count == null || count == 0) {
            return Optional.empty();
        }
        String selectSql = "SELECT id, username, phone, date, description, location_id, password, active, r.role_name FROM users u JOIN roles r on u.role_id = r.role_id WHERE id = ?";

        return Optional.ofNullable(jdbcTemplate.queryForObject(selectSql, new UserRowMapper()));
    }
}