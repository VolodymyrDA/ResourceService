package org.vdoloka.service.impl;

import org.vdoloka.entity.UserEntity;
import org.vdoloka.repository.impl.UsersRepositoryImpl;
import org.vdoloka.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepositoryImpl usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersServiceImpl(UsersRepositoryImpl usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void addUser(UserEntity userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setActive(true);
        userEntity.setDate(LocalDateTime.now());
        usersRepository.addUser(userEntity);
    }

    @Override
    public void updateUser(UserEntity userEntity) {
        if (!userEntity.getPassword().isEmpty())
            userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        usersRepository.updateUser(userEntity);
    }

    @Override
    public UserEntity findByUserID(int id) {
        UserEntity userEntity = usersRepository.findByUserID(id);
        userEntity.setPassword("");
        return userEntity;
    }
}