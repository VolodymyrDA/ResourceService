package org.vdoloka.service.impl;

import org.vdoloka.entity.User;
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
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        user.setDate(LocalDateTime.now());
        usersRepository.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        if (!user.getPassword().isEmpty())
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersRepository.updateUser(user);
    }

    @Override
    public User findByUserID(int id) {
        User user = usersRepository.findByUserID(id);
        user.setPassword("");
        return user;
    }
}