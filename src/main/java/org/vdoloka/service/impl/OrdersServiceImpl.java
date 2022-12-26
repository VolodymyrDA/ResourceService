package org.vdoloka.service.impl;

import org.vdoloka.dto.HubOrderDTO;
import org.vdoloka.dto.OrderDto;
import org.vdoloka.repository.impl.HubsRepositoryImpl;
import org.vdoloka.repository.impl.OrdersRepositoryImpl;
import org.vdoloka.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepositoryImpl ordersRepository;
    private final HubsRepositoryImpl hubsRepository;

    @Autowired
    public OrdersServiceImpl(OrdersRepositoryImpl ordersRepository, HubsRepositoryImpl hubsRepository) {
        this.ordersRepository = ordersRepository;
        this.hubsRepository = hubsRepository;
    }

    @Override
    public void addOrder(OrderDto orderDto) {
        ordersRepository.addOrder(orderDto);
    }

    @Override
    public void confirmOrder(int orderId) {
        hubsRepository.reduceResourceQuantityByOrder(orderId);
        ordersRepository.confirmOrder(orderId);
    }

    @Override
    public List<OrderDto> getOrders(int page, int itemPerPage) {
        List<OrderDto> orderlist = ordersRepository.getOrders(page, itemPerPage);
        return orderlist;
    }

    @Override
    public List<HubOrderDTO> getHubOrders(int page, int itemPerPage) {
        List<HubOrderDTO> hubOrderlist = ordersRepository.getHubOrders(page, itemPerPage);
        return hubOrderlist;
    }

    @Override
    public List<HubOrderDTO> getConfirmedOrders(int page, int itemPerPage) {
        List<HubOrderDTO> hubOrderlist = ordersRepository.getConfirmedOrders(page, itemPerPage);
        return hubOrderlist;
    }

}