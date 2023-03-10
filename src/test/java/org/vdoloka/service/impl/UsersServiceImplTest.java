package org.vdoloka.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.vdoloka.entity.User;
import org.vdoloka.repository.UsersRepository;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsersServiceImplTest {
    @Mock
    private UsersRepository usersRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UsersServiceImpl usersService;

    @Test
    @ExtendWith(MockitoExtension.class)
    void shouldEncodePasswordAndSetDateAndActive() {
        User user = User.builder()
                .username("johnDoe")
                .password("password")
                .build();
        when(usersRepository.createUser(user)).thenReturn(1L);

        usersService.createUser(user);

        assertThat(user.getPassword()).isNotEqualTo("password");
        assertThat(user.getDate()).isBeforeOrEqualTo(LocalDateTime.now());
        verify(usersRepository).createUser(user);
    }

    @Test
    void shouldReturnUserWithMatchingID() {
        User expectedUser = User.builder()
                .id(1)
                .username("johnDoe")
                .password("password")
                .build();
        when(usersRepository.findByUserID(1)).thenReturn(java.util.Optional.of(expectedUser));

        User actualUser = usersService.findByUserID(1);

        assertThat(actualUser).usingRecursiveComparison().ignoringFields("password").isEqualTo(expectedUser);
        verify(usersRepository).findByUserID(1);
    }

    @Test
    void shouldThrowUsernameNotFoundExceptionWhenUserNotFound() {
        when(usersRepository.findByUserID(1)).thenReturn(java.util.Optional.empty());

        assertThatThrownBy(() -> usersService.findByUserID(1))
                .isInstanceOf(UsernameNotFoundException.class)
                .hasMessageContaining("User not found with id: 1");
        verify(usersRepository).findByUserID(1);
    }
}