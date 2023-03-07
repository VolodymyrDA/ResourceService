package org.vdoloka.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.vdoloka.dto.UserDTO;
import org.vdoloka.entity.Location;
import org.vdoloka.entity.User;
import org.vdoloka.service.NomenclatureService;
import org.vdoloka.service.UsersService;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegistrationControllerTest {

    @Mock
    private UsersService usersService;

    @Mock
    private NomenclatureService nomenclatureService;

    @InjectMocks
    private RegistrationController registrationController;

    @Test
    void shouldReturnRegistrationPage() {
        List<Location> locations = Arrays.asList(new Location(), new Location());
        when(nomenclatureService.getLocations()).thenReturn(locations);
        Model model = mock(Model.class);

        String viewName = registrationController.registration(model);

        assertThat(viewName).isEqualTo("registration");
        verify(model).addAttribute("locations", locations);
    }

    @Test
    void shouldCreateNewUser() {
        UserDTO userDTO = new UserDTO();
        Model model = mock(Model.class);

        String viewName = registrationController.registerUser(userDTO, model);

        assertThat(viewName).isEqualTo("login");
        verify(usersService).createUser(any(User.class));
        verify(model).addAttribute("message", "Registration Success");
    }
}