package org.vdoloka.controller.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.vdoloka.dto.HubOrderDTO;
import org.vdoloka.service.OrdersService;


@ExtendWith(MockitoExtension.class)
class HubOrdersControllerTest {
    @InjectMocks
    private HubOrdersController hubOrdersController;

    @Mock
    private OrdersService ordersServiceImpl;

    @Test
    void shouldReturnOrdersListWhenGetOrders() {
        List<HubOrderDTO> expected = Arrays.asList(
                HubOrderDTO.builder().build(),
                HubOrderDTO.builder().build()
        );

        when(ordersServiceImpl.getHubOrders(1, 10)).thenReturn(expected);

        List<HubOrderDTO> result = hubOrdersController.getOrdersPageData(1, 10);

        verify(ordersServiceImpl, times(1)).getHubOrders(1, 10);
        assertEquals(expected, result);
    }

    @Test
    void shouldReturnConfirmedOrdersList() {
        List<HubOrderDTO> expected = Arrays.asList(
                HubOrderDTO.builder().build(),
                HubOrderDTO.builder().build()
        );

        when(ordersServiceImpl.getConfirmedOrders(1, 10)).thenReturn(expected);

        List<HubOrderDTO> result = hubOrdersController.getConfirmedOrdersPageData(1, 10);

        verify(ordersServiceImpl, times(1)).getConfirmedOrders(1, 10);
        assertEquals(expected, result);
    }

    @Test
    void shouldCallConfirmOrderOnOrdersService() {
        hubOrdersController.confirmOrderById(1);

        verify(ordersServiceImpl, times(1)).confirmOrder(1);
    }
}