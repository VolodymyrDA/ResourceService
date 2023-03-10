package org.vdoloka.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.vdoloka.dto.HubResourcesDTO;
import org.vdoloka.model.AnalyticsType;
import org.vdoloka.repository.HubsRepository;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResourcesServiceImplTest {
    @Mock
    private HubsRepository hubsRepository;
    @InjectMocks
    private ResourcesServiceImpl resourcesService;

    @Test
    void testGetAnalyticsWithResourcesOnHubs() {
        when(hubsRepository.getAllResources(anyInt(), anyInt())).thenReturn(Collections.singletonList(HubResourcesDTO.builder().build()));

        List<HubResourcesDTO> hubResourcesDTOList = resourcesService.getAnalytics(AnalyticsType.RESOURCES_ON_HUBS, 0, 10);

        assertThat(hubResourcesDTOList).hasSize(1);
    }

    @Test
    void shouldGetAnalyticsWithLackResources() {
        when(hubsRepository.getLackResources(anyInt(), anyInt())).thenReturn(Collections.singletonList(HubResourcesDTO.builder().build()));

        List<HubResourcesDTO> hubResourcesDTOList = resourcesService.getAnalytics(AnalyticsType.LACK_RESOURCES, 0, 10);

        assertThat(hubResourcesDTOList).hasSize(1);
    }

    @Test
    void shouldGetAnalyticsWithTopOrderingResources() {
        when(hubsRepository.getCountOrderingResources(anyInt(), anyInt())).thenReturn(Collections.singletonList(HubResourcesDTO.builder().build()));

        List<HubResourcesDTO> hubResourcesDTOList = resourcesService.getAnalytics(AnalyticsType.TOP_ORDERING_RESOURCES, 0, 10);

        assertThat(hubResourcesDTOList).hasSize(1);
    }

    @Test
    void shouldGetResources() {
        when(hubsRepository.getResources(anyInt(), anyInt())).thenReturn(Collections.singletonList(HubResourcesDTO.builder().build()));

        List<HubResourcesDTO> hubResourcesDTOList = resourcesService.getResources(0, 10);

        assertThat(hubResourcesDTOList).hasSize(1);
    }

    @Test
    void shouldIncreaseResourceQuantityBySupplement() {
        HubResourcesDTO hubResourcesDTO = HubResourcesDTO.builder().build();

        resourcesService.increaseResourceQuantityBySupplement(hubResourcesDTO);

        verify(hubsRepository).increaseResourceQuantityBySupplement(hubResourcesDTO);
    }
}