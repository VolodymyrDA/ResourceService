package org.vdoloka.repository;

import org.vdoloka.entity.User;


public interface UsersRepository {
    void addUser(User user);

    void updateUser(User user);

    User findByUsername(String username);

    User findByUserID(int id);
}