package org.vdoloka.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.vdoloka.service.UsersService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringJUnitConfig
class GoogleAuthenticationSuccessHandlerTest {
    @Mock
    private UsersService usersService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private Authentication authentication;

    @Test
    @WithMockUser(username = "test")
    void shouldCorrectRedirectExistingUser() throws IOException {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("sub", "12345");
        attributes.put("email", "test@example.com");
        attributes.put("name", "Test User");

        OAuth2User principal = new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("USER")),
                attributes,
                "sub"
        );

        when(authentication.getPrincipal()).thenReturn(principal);
        when(usersService.isGoogleUserExist("12345")).thenReturn(true);

        GoogleAuthenticationSuccessHandler handler = new GoogleAuthenticationSuccessHandler(usersService);
        handler.onAuthenticationSuccess(request, response, authentication);

        verify(response).sendRedirect(request.getContextPath() + "/orders");
    }

    @Test
    @WithMockUser(username = "test")
    void shouldCorrectCreateAndRedirectNewUser() throws IOException {
        // Create a valid OAuth2User object with populated attributes
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("sub", "12345");
        attributes.put("email", "test@example.com");
        attributes.put("name", "Test User");

        OAuth2User principal = new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("USER")),
                attributes,
                "sub"
        );

        when(authentication.getPrincipal()).thenReturn(principal);
        when(usersService.isGoogleUserExist("12345")).thenReturn(false);

        GoogleAuthenticationSuccessHandler handler = new GoogleAuthenticationSuccessHandler(usersService);
        handler.onAuthenticationSuccess(request, response, authentication);

        verify(usersService).createUser(principal);
        verify(response).sendRedirect(request.getContextPath() + "/profile");
    }
}