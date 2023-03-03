package org.vdoloka.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.vdoloka.dto.OrderDto;
import org.vdoloka.exeption.OrderNotFoundException;
import org.vdoloka.repository.impl.HubsRepositoryImpl;
import org.vdoloka.repository.impl.OrdersRepositoryImpl;

class OrdersServiceImplImplTest {

    @Mock
    private OrdersRepositoryImpl ordersRepository;

    @Mock
    private HubsRepositoryImpl hubsRepository;

    @InjectMocks
    private OrdersServiceImpl ordersServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addOrder_ShouldInvokeRepositoryAddOrderMethod() {
        OrderDto orderDto = OrderDto.builder().build();
        ordersServiceImpl.addOrder(orderDto);
        verify(ordersRepository).addOrder(orderDto);
    }

    @Test
    void confirmOrder_ShouldReduceResourceQuantityAndConfirmOrder_WhenOrderExists() {
        int orderId = 1;
        when(ordersRepository.isOrderExist(orderId)).thenReturn(true);
        ordersServiceImpl.confirmOrder(orderId);
        verify(hubsRepository).reduceResourceQuantityByOrder(orderId);
        verify(ordersRepository).confirmOrder(orderId);
    }

    @Test
    void confirmOrder_ShouldThrowOrderNotFoundException_WhenOrderDoesNotExist() {
        int orderId = 1;
        when(ordersRepository.isOrderExist(orderId)).thenReturn(false);
        Throwable throwable = catchThrowable(() -> ordersServiceImpl.confirmOrder(orderId));
        assertThat(throwable).isInstanceOf(OrderNotFoundException.class);
    }
}