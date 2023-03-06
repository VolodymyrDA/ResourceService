package org.vdoloka.config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.vdoloka.controller.GoogleAuthenticationSuccessHandler;
import org.vdoloka.entity.User;
import org.vdoloka.repository.UsersRepository;
import org.vdoloka.service.UsersService;

class GoogleAuthenticationSuccessHandlerTest {
    @Mock
    private UsersService usersService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    private GoogleAuthenticationSuccessHandler successHandler;
    OAuth2User oauth2User;
    private AutoCloseable closeable;

    @BeforeEach
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        successHandler = new GoogleAuthenticationSuccessHandler(usersService);
        oauth2User = new DefaultOAuth2User(
                null,
                Map.of("sub", "test_sub", "given_name", "test_name"),
                "sub"
        );
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }

    @Test
    void shouldAuthenticationSuccessWhenUserExist() throws IOException {

        when(usersService.isGoogleUserExist("test_sub")).thenReturn(true);
        User user = User.builder()
                .id(1L)
                .role("ROLE_USER")
                .username("test_name")
                .password("test_sub")
                .build();
        when(usersService.("test_sub")).thenReturn(Optional.of(user));

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                oauth2User,
                null,
                oauth2User.getAuthorities()
        );

        successHandler.onAuthenticationSuccess(request, response, authentication);

        verify(usersService.isGoogleUserExist("test_sub");
        verify(usersService.findByUserSub("test_sub");
        verify(response).sendRedirect((request.getContextPath() + "/orders"));

        Authentication resultAuth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal principal = (UserPrincipal) resultAuth.getPrincipal();
        assertThat(principal)
                .extracting(UserPrincipal::getUsername, UserPrincipal::getPassword, UserPrincipal::getRole)
                .containsExactly("test_name", "test_sub", "ROLE_USER");
    }

    @Test
    void shouldAuthenticationSuccessForNewUser() throws IOException {

        when(usersRepository.isGoogleUserExist("test_sub")).thenReturn(false);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                oauth2User,
                null,
                oauth2User.getAuthorities()
        );

        successHandler.onAuthenticationSuccess(request, response, authentication);

        verify(usersRepository).isGoogleUserExist(("test_sub"));
        verify(usersRepository).createUser(any(User.class));

        verify(response).sendRedirect((request.getContextPath() + "/profile"));

        Authentication resultAuth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal resultUser = (UserPrincipal) resultAuth.getPrincipal();

        assertThat(resultUser.getUsername())
                .isEqualTo(oauth2User.getAttribute("given_name"));
        assertThat(resultUser.getPassword())
                .isEqualTo("test_sub");
        assertThat(resultUser.getRole())
                .isEqualTo("ROLE_USER");
    }
}