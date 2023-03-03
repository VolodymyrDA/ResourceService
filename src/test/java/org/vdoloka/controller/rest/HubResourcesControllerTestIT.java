package org.vdoloka.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.vdoloka.dto.HubResourcesDTO;
import org.vdoloka.repository.impl.HubsRepository;

import static java.util.Collections.emptyList;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class HubResourcesControllerTestIT {
    @Mock
    private HubsRepository hubsRepository;

    @InjectMocks
    private HubResourcesController hubResourcesController;

    @Autowired
    private MockMvc mockMvc;

    @Test
  void getEntries_ShouldReturnSuccessStatusAndCallRepository() throws Exception {
        when(hubsRepository.getResources(1, 10)).thenReturn(emptyList());
        mockMvc = MockMvcBuilders.standaloneSetup(hubResourcesController).build();
        mockMvc.perform(get("/hubResources/?page=1&itemPerPage=10").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(hubsRepository, times(1)).getResources(1, 10);
    }

    @Test
    @WithMockUser(roles = "USER")
    void supplementHubResources_ShouldReturnSuccessStatusAndCallRepository() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(hubResourcesController).build();
        HubResourcesDTO hubResourcesDTO = HubResourcesDTO.builder().resourceId(1).quantity(1).resourceName("test").build();
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(patch("/hubResources/").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(hubResourcesDTO)))
                .andExpect(status().isOk());
    }
}
