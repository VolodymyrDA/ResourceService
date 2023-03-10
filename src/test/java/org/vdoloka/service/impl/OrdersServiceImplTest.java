package org.vdoloka.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.vdoloka.dto.OrderDto;
import org.vdoloka.exception.OrderNotFoundException;
import org.vdoloka.repository.impl.HubsRepositoryImpl;
import org.vdoloka.repository.impl.OrdersRepositoryImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrdersServiceImplTest {
    @Mock
    private OrdersRepositoryImpl ordersRepository;
    @Mock
    private HubsRepositoryImpl hubsRepository;
    @InjectMocks
    private OrdersServiceImpl ordersService;

    @Test
    void addOrder_ShouldInvokeRepositoryAddOrderMethod() {
        OrderDto orderDto = OrderDto.builder().build();
        ordersService.addOrder(orderDto);
        verify(ordersRepository).addOrder(orderDto);
    }

    @Test
    void confirmOrder_ShouldReduceResourceQuantityAndConfirmOrder_WhenOrderExists() {
        int orderId = 1;
        when(ordersRepository.isOrderExist(orderId)).thenReturn(true);
        ordersService.confirmOrder(orderId);
        verify(hubsRepository).reduceResourceQuantityByOrder(orderId);
        verify(ordersRepository).confirmOrder(orderId);
    }

    @Test
    void confirmOrder_ShouldThrowOrderNotFoundException_WhenOrderDoesNotExist() {
        int orderId = 1;
        when(ordersRepository.isOrderExist(orderId)).thenReturn(false);
        Throwable throwable = catchThrowable(() -> ordersService.confirmOrder(orderId));
        assertThat(throwable).isInstanceOf(OrderNotFoundException.class);
    }
}