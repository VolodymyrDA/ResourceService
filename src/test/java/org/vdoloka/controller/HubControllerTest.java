package org.vdoloka.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.vdoloka.entity.Location;
import org.vdoloka.entity.User;
import org.vdoloka.service.NomenclatureService;
import org.vdoloka.service.UsersService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HubControllerTest {

    @Mock
    private UsersService usersService;

    @Mock
    private NomenclatureService nomenclatureService;

    @Mock
    private Model model;

    @InjectMocks
    private HubController controller;

    @Test
    void testViewHub() {
        int hubId = 1;
        User user = User.builder().build();
        user.setId(hubId);
        List<Location> locations = new ArrayList<>();
        when(nomenclatureService.getLocations()).thenReturn(locations);
        when(usersService.findByUserID(hubId)).thenReturn(user);

        String viewName = controller.viewHub(hubId, model);

        assertThat(viewName).isEqualTo("hub");
        verify(model).addAttribute("locations", locations);
        verify(model).addAttribute("hub", user);
    }
}