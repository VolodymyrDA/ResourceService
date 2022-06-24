package org.vdoloka.repository;

import org.vdoloka.entity.UserEntity;


public interface UsersRepository {
    void addUser(UserEntity userEntity);

    void updateUser(UserEntity userEntity);

    UserEntity findByUsername(String username);

    UserEntity findByUserID(int id);
}