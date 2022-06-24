package org.vdoloka.service.impl;

import org.vdoloka.DTO.HubOrderDTO;
import org.vdoloka.entity.OrderEntity;
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
    public void addOrder(OrderEntity orderEntity) {
        ordersRepository.addOrder(orderEntity);
    }

    @Override
    public void confirmOrder(int orderId) {
        hubsRepository.reduceResourceQuantityByOrder(orderId);
        ordersRepository.confirmOrder(orderId);
    }

    @Override
    public List<OrderEntity> getOrders(int page, int itemPerPage) {
        List<OrderEntity> orderlist = ordersRepository.getOrders(page, itemPerPage);
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