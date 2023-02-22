package org.vdoloka.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.vdoloka.entity.User;
import org.vdoloka.repository.impl.UsersRepositoryImpl;
import org.vdoloka.service.UsersService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UsersRepositoryImpl usersRepository;
    private final PasswordEncoder passwordEncoder;


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
    public User findByUserID(int id) throws UsernameNotFoundException {
        User user = usersRepository.findByUserID(id).orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));
        user.setPassword("");
        return user;
    }
}