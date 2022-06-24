package org.vdoloka.repository.impl;

import org.vdoloka.config.UserPrincipal;
import org.vdoloka.entity.UserEntity;
import org.vdoloka.repository.UsersRepository;
import org.vdoloka.repository.mapper.UserRowMapper;
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
    public void addUser(UserEntity userEntity) {
        final int newUserRoleId = 2;
        String sql = "INSERT INTO users (username, password ,description ,phone,date,location_id,active,role_id)" +
                " VALUES (:userName,:password,:description,:phone,:date,:location,:active,:roleId)";
        namedjdbcTemplate.update(sql,
                Map.of("userName", userEntity.getUsername(), "password", userEntity.getPassword(),
                        "description", userEntity.getDescription(), "phone", userEntity.getPhone(),
                        "date", userEntity.getDate(), "location", userEntity.getLocationId(),
                        "active", userEntity.getActive(), "roleId", newUserRoleId)
        );
    }

    public void updateUser(UserEntity userEntity) {
        String sql = "UPDATE users u SET ";
        if (userEntity.getUsername() != null) {
            sql = sql + "username = '" + userEntity.getUsername() + "'";
        }
        if (!userEntity.getPassword().isEmpty()) {
            sql = sql + ",password = '" + userEntity.getPassword() + "'";
        }
        if (userEntity.getDescription() != null) {
            sql = sql + ",description = '" + userEntity.getDescription() + "'";
        }
        if (userEntity.getPhone() != null) {
            sql = sql + ",phone = '" + userEntity.getPhone() + "'";
        }
        if (userEntity.getLocationId() != 0) {
            sql = sql + ",location_id = '" + userEntity.getLocationId() + "'";
        }
        sql = sql + " WHERE u.id=" + getCurrentUserId();
        jdbcTemplate.execute(sql);
    }

    public UserEntity findByUsername(String username) {
        String sql = "SELECT id,username,phone,date,description,location_id,password,active,r.role_name  FROM users u JOIN roles r on u.role_id = r.role_id " +
                "where username= '" + username + "'";
        UserEntity userEntity = jdbcTemplate.queryForObject(sql, new UserRowMapper());
        return userEntity;
    }

    @Override
    public UserEntity findByUserID(int id) {
        String sql = "SELECT id,username,phone,date,description,location_id,password,active,r.role_name  FROM users u JOIN roles r on u.role_id = r.role_id " +
                "where id= '" + id + "'";
        UserEntity userEntity = jdbcTemplate.queryForObject(sql, new UserRowMapper());
        return userEntity;
    }
}