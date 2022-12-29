package org.vdoloka.repository.impl;

import org.vdoloka.config.UserPrincipal;
import org.vdoloka.entity.User;
import org.vdoloka.repository.UsersRepository;
import org.vdoloka.repository.row_mapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UsersRepositoryImpl implements UsersRepository {
    private final NamedParameterJdbcTemplate namedjdbcTemplate;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UsersRepositoryImpl(NamedParameterJdbcTemplate namedjdbcTemplate, JdbcTemplate jdbcTemplate) {
        this.namedjdbcTemplate = namedjdbcTemplate;
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getCurrentUserId() {
        return ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }

    @Override
    public void addUser(User user) {
        final int newUserRoleId = 2;
        String sql = "INSERT INTO users (username, password ,description ,phone,date,location_id,active,role_id)" +
                " VALUES (:userName,:password,:description,:phone,:date,:location,:active,:roleId)";
        namedjdbcTemplate.update(sql,
                Map.of("userName", user.getUsername(), "password", user.getPassword(),
                        "description", user.getDescription(), "phone", user.getPhone(),
                        "date", user.getDate(), "location", user.getLocationId(),
                        "active", user.getActive(), "roleId", newUserRoleId)
        );
    }

    public void updateUser(User user) {
        String sql = "UPDATE users u SET ";
        if (user.getUsername() != null) {
            sql = sql + "username = '" + user.getUsername() + "'";
        }
        if (!user.getPassword().isEmpty()) {
            sql = sql + ",password = '" + user.getPassword() + "'";
        }
        if (user.getDescription() != null) {
            sql = sql + ",description = '" + user.getDescription() + "'";
        }
        if (user.getPhone() != null) {
            sql = sql + ",phone = '" + user.getPhone() + "'";
        }
        if (user.getLocationId() != 0) {
            sql = sql + ",location_id = '" + user.getLocationId() + "'";
        }
        sql = sql + " WHERE u.id=" + getCurrentUserId();
        jdbcTemplate.execute(sql);
    }

    public User findByUsername(String username) {
        String sql = "SELECT id,username,phone,date,description,location_id,password,active,r.role_name  FROM users u JOIN roles r on u.role_id = r.role_id " +
                "where username= '" + username + "'";
        return jdbcTemplate.queryForObject(sql, new UserRowMapper());
    }

    @Override
    public User findByUserID(int id) {
        String sql = "SELECT id,username,phone,date,description,location_id,password,active,r.role_name  FROM users u JOIN roles r on u.role_id = r.role_id " +
                "where id= '" + id + "'";
        return jdbcTemplate.queryForObject(sql, new UserRowMapper());
    }
}