package org.vdoloka.controller.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.vdoloka.dto.HubResourcesDTO;
import org.vdoloka.model.AnalyticsType;
import org.vdoloka.service.impl.ResourcesServiceImpl;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnalyticsControllerTest {

    @Mock
    private ResourcesServiceImpl resourcesServiceImpl;

    @InjectMocks
    private AnalyticsController analyticsController;

    @Test
    void testGetAnalytics() {
        AnalyticsType type = AnalyticsType.RESOURCES_ON_HUBS;
        int page = 1;
        int itemsPerPage = 10;
        List<HubResourcesDTO> expectedData = List.of((
                        HubResourcesDTO.builder().resourceId(1).quantity(2).build()),
                HubResourcesDTO.builder().resourceId(1).quantity(2).build());
        when(resourcesServiceImpl.getAnalytics(eq(type), anyInt(), anyInt())).thenReturn(expectedData);

        List<HubResourcesDTO> actualData = analyticsController.getAnalytics(type, page, itemsPerPage);

        assertThat(actualData).isEqualTo(expectedData);
    }
}
