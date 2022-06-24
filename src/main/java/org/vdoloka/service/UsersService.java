package org.vdoloka.service;

import org.vdoloka.entity.UserEntity;

public interface UsersService {

    void addUser(UserEntity userEntity);

    void updateUser(UserEntity userEntity);

    UserEntity findByUserID(int id);
}