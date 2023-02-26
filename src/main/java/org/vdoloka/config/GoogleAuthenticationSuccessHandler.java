package org.vdoloka.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.vdoloka.entity.User;
import org.vdoloka.repository.impl.UsersRepositoryImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class GoogleAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
        private final UsersRepositoryImpl usersRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2User oauth2User = (DefaultOAuth2User) authentication.getPrincipal();
        String userSub= oauth2User.getAttribute("sub");
        if (usersRepository.isUserExist(userSub)){
            UserPrincipal principal = new UserPrincipal(usersRepository.findByUserSub(userSub).get());
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities()));
            response.sendRedirect(request.getContextPath() + "/orders");
        }
       else {
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
            user.setId(usersRepository.addUser(user));
            UserPrincipal principal = new UserPrincipal(user);
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities()));
            response.sendRedirect(request.getContextPath() + "/profile");
        }
    }
}