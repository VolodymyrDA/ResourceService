package org.vdoloka.controller.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.vdoloka.dto.OrderDto;
import org.vdoloka.dto.OrderInfoDto;
import org.vdoloka.service.impl.OrdersServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class OrdersControllerTest {

    @Mock
    private OrdersServiceImpl ordersService;

    @InjectMocks
    private OrdersController ordersController;

    private MockMvc mockMvc;

    @Test
    void ShouldReturnOrdersWhenGetOrders() throws Exception {
        List<OrderInfoDto> orderInfoDtos = Arrays.asList(OrderInfoDto.builder().build(), OrderInfoDto.builder().build());
        when(ordersService.getOrders(1, 10)).thenReturn(orderInfoDtos);

        mockMvc = MockMvcBuilders.standaloneSetup(ordersController).build();
        mockMvc.perform(get("/orders/")
                        .param("page", "1")
                        .param("itemPerPage", "10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[{},{}]"));
    }

    @Test
    void shouldCallOrdersServiceWhenPostOrder() {
        OrderDto orderDto = OrderDto.builder().build();

        ordersController.addOrder(orderDto);

        verify(ordersService, times(1)).addOrder(orderDto);
    }
}