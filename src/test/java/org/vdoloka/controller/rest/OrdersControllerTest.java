package org.vdoloka.controller.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.vdoloka.dto.OrderDto;
import org.vdoloka.dto.OrderInfoDto;
import org.vdoloka.model.SortDirection;
import org.vdoloka.service.impl.OrdersServiceImpl;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrdersControllerTest {

    @Mock
    private OrdersServiceImpl ordersServiceImpl;

    @InjectMocks
    private OrdersController ordersController;

    @Test
    void ShouldGetOrders() {
        List<OrderInfoDto> orderInfoDto = List.of(OrderInfoDto.builder().id(1).hubId(1).resourceId(1).quantity(1).build(),
                OrderInfoDto.builder().id(1).hubId(1).resourceId(1).quantity(1).build());

        when(ordersServiceImpl.getOrders(anyInt(), anyInt(), anyString(), any(SortDirection.class))).thenReturn(orderInfoDto);

        List<OrderInfoDto> result = ordersController.getOrders(1, 10, "id", SortDirection.DESC);

        assertThat(result)
                .isNotNull()
                .hasSize(orderInfoDto.size())
                .extracting(OrderInfoDto::getHubId)
                .containsExactly(orderInfoDto.get(0).getHubId(), orderInfoDto.get(1).getHubId());
    }

    @Test
    void shouldCallOrdersServiceWhenPostOrder() {
        OrderDto orderDto = OrderDto.builder().build();

        ordersServiceImpl.addOrder(orderDto);

        verify(ordersServiceImpl, times(1)).addOrder(orderDto);
    }
}