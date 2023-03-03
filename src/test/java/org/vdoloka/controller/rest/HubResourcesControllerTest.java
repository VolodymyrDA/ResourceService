package org.vdoloka.controller.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.vdoloka.dto.HubResourcesDTO;
import org.vdoloka.repository.impl.HubsRepositoryImpl;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HubResourcesControllerTest {
    @InjectMocks
    private HubResourcesController hubResourcesController;
    @Mock
    private HubsRepositoryImpl hubsRepository;

    @Test
    void shouldGetEntries() {
        List<HubResourcesDTO> expected = List.of(HubResourcesDTO.builder().build());
        when(hubsRepository.getResources(anyInt(), anyInt())).thenReturn(expected);

        List<HubResourcesDTO> actual = hubResourcesController.getEntries(1, 10);

        assertThat(actual).isEqualTo(expected);
        verify(hubsRepository, times(1)).getResources(1, 10);
    }

    @Test
    void shouldSupplementHubResources() {
        HubResourcesDTO hubResourcesDTO = HubResourcesDTO.builder().build();
        doNothing().when(hubsRepository).increaseResourceQuantityBySupplement(hubResourcesDTO);

        hubResourcesController.supplementHubResources(hubResourcesDTO);

        verify(hubsRepository, times(1)).increaseResourceQuantityBySupplement(hubResourcesDTO);
    }
}