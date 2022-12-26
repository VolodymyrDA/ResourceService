package org.vdoloka.service;

import org.vdoloka.entity.User;

public interface UsersService {

    void addUser(User user);

    void updateUser(User user);

    User findByUserID(int id);
}