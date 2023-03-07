package org.vdoloka.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.vdoloka.dto.UserDTO;
import org.vdoloka.dto.mapper.UserMapper;
import org.vdoloka.entity.Location;
import org.vdoloka.service.NomenclatureService;
import org.vdoloka.service.UsersService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfileControllerTest {

    @Mock
    private UsersService usersService;

    @Mock
    private NomenclatureService nomenclatureService;

    @InjectMocks
    private ProfileController profileController;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @Test
    void shouldViewProfile() {
        List<Location> locations = new ArrayList<>();
        locations.add(new Location(1, "Location 1"));
        locations.add(new Location(2, "Location 2"));

        when(nomenclatureService.getLocations()).thenReturn(locations);

        String viewName = profileController.viewProfile(model);

        assertThat(viewName).isEqualTo("profile");
        verify(nomenclatureService).getLocations();
        verify(model).addAttribute("locations", locations);
    }

    @Test
    void shouldUpdateProfile_withValidData() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("johnDoe");
        userDTO.setPassword("password");
        userDTO.setLocationId(1);

        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName = profileController.updateProfile(userDTO, bindingResult, model);

        assertThat(viewName).isEqualTo("login");
        verify(usersService).updateUser(UserMapper.INSTANCE.toEntity(userDTO));
        verify(model).addAttribute("message", "Update Success");
    }

    @Test
    void shouldUpdateProfile_withInvalidData() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("");
        userDTO.setPassword("");
        userDTO.setLocationId(0);

        when(bindingResult.hasErrors()).thenReturn(true);

        List<FieldError> errors = new ArrayList<>();
        errors.add(new FieldError("userDTO", "username", "Username is required"));
        errors.add(new FieldError("userDTO", "password", "Password is required"));
        errors.add(new FieldError("userDTO", "locationId", "Location is required"));

        when(bindingResult.getFieldErrors()).thenReturn(errors);

        List<Location> locations = new ArrayList<>();
        locations.add(new Location(1, "Location 1"));
        locations.add(new Location(2, "Location 2"));

        when(nomenclatureService.getLocations()).thenReturn(locations);

        String viewName = profileController.updateProfile(userDTO, bindingResult, model);

        assertThat(viewName).isEqualTo("profile");
        verify(bindingResult).hasErrors();
        verify(bindingResult).getFieldErrors();
        verify(nomenclatureService).getLocations();
        verify(model).addAttribute("errors", errors);
        verify(model).addAttribute("locations", locations);
    }
}