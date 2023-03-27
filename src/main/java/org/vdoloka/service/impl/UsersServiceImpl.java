package org.vdoloka.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.vdoloka.config.UserPrincipal;
import org.vdoloka.entity.User;
import org.vdoloka.repository.UsersRepository;
import org.vdoloka.service.UsersService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(Boolean.TRUE);
        user.setDate(LocalDateTime.now());
        user.setSub("0");
        usersRepository.createUser(user);
    }

    @Override
    public void createUser(OAuth2User oauth2User) {
        String userSub = oauth2User.getAttribute("sub");
        User user = User.builder()
                .username(oauth2User.getAttribute("given_name"))
                .password(userSub)
                .date(LocalDateTime.now())
                .active(true)
                .role("ROLE_USER")
                .sub(userSub)
                .phone("")
                .locationId(1)
                .description(oauth2User.getAttribute("given_name"))
                .build();
        user.setId(usersRepository.createUser(user));
        addUserToContext(user);
    }

    private void addUserToContext(User user) {
        UserPrincipal principal = new UserPrincipal(user);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities()));
    }

    private void addUserToContext(String userSub) {
        UserPrincipal principal = new UserPrincipal(usersRepository.findByUserSub(userSub).get());
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities()));
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

    @Override
    public boolean isGoogleUserExist(String userSub) {
        addUserToContext(userSub);
        return usersRepository.isGoogleUserExist(userSub);
    }
}