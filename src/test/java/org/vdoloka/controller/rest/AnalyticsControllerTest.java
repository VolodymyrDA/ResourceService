package org.vdoloka.controller.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.vdoloka.dto.HubResourcesDTO;
import org.vdoloka.service.impl.AnalyticsServiceImpl;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AnalyticsControllerTest {
    private MockMvc mockMvc;

    @Mock
    private AnalyticsServiceImpl analyticsService;

    @InjectMocks
    private AnalyticsController analyticsController;

    @Test
    void shouldGetAnalyticsEntries() throws Exception {
        List<HubResourcesDTO> resourcesOnHubs = List.of();
        when(analyticsService.getResourcesOnHubs(1, 10)).thenReturn(resourcesOnHubs);

        mockMvc = MockMvcBuilders.standaloneSetup(analyticsController).build();
        mockMvc.perform(get("/analytics/?page=1&itemPerPage=10"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetAnalyticsLackEntries() throws Exception {
        List<HubResourcesDTO> lackResources = List.of();
        when(analyticsService.getLackResources(1, 10)).thenReturn(lackResources);

        mockMvc = MockMvcBuilders.standaloneSetup(analyticsController).build();
        mockMvc.perform(get("/analytics/lack/?page=1&itemPerPage=10"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetAnalyticsTopEntries() throws Exception {
        List<HubResourcesDTO> countOrderingResources = List.of();
        when(analyticsService.getCountOrderingResources(1, 10)).thenReturn(countOrderingResources);

        mockMvc = MockMvcBuilders.standaloneSetup(analyticsController).build();
        mockMvc.perform(get("/analytics/top/?page=1&itemPerPage=10"))
                .andExpect(status().isOk());
    }
}