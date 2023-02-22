package org.vdoloka.repository;

import org.vdoloka.entity.User;

import java.util.Optional;


public interface UsersRepository {
    void addUser(User user);

    void updateUser(User user);

    Optional<User> findByUsername(String username);

    Optional<User> findByUserID(int id);
}