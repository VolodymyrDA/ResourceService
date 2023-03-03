package org.vdoloka.controller.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.vdoloka.dto.CategoryDTO;
import org.vdoloka.dto.OrderDto;
import org.vdoloka.dto.OrderInfoDto;
import org.vdoloka.entity.Category;
import org.vdoloka.model.SortDirection;
import org.vdoloka.service.impl.OrdersService;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class OrdersControllerTest {

    @Mock
    private OrdersService ordersService;

    @InjectMocks
    private OrdersController ordersController;

    private MockMvc mockMvc;

    @Test
    void ShouldGetOrders() throws Exception {
        List<OrderInfoDto> orderInfoDto = List.of(OrderInfoDto.builder().id(1).hubId(1).resourceId(1).quantity(1).build(),
                OrderInfoDto.builder().id(1).hubId(1).resourceId(1).quantity(1).build());

        when(ordersService.getOrders(anyInt(), anyInt(), anyString(), any(SortDirection.class))).thenReturn(orderInfoDto);

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

        ordersService.addOrder(orderDto);

        verify(ordersService, times(1)).addOrder(orderDto);
    }
}