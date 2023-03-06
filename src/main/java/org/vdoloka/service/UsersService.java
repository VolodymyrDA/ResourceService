package org.vdoloka.service;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.vdoloka.entity.User;

public interface UsersService {

    void createUser(User user);

    void createUser(OAuth2User oauth2User);

    void updateUser(User user);

    User findByUserID(int id);

    boolean isGoogleUserExist(String sub);
}