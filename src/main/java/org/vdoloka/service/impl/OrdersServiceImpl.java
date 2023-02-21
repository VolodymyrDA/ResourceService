package org.vdoloka.service.impl;

import org.vdoloka.dto.HubOrderDTO;
import org.vdoloka.dto.OrderDto;
import org.vdoloka.dto.OrderInfoDto;
import org.vdoloka.exeption.OrderNotFoundException;
import org.vdoloka.model.SortDirection;
import org.vdoloka.repository.impl.HubsRepositoryImpl;
import org.vdoloka.repository.impl.OrdersRepositoryImpl;
import org.vdoloka.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    @Transactional
    public void confirmOrder(int orderId) {
        if (!ordersRepository.isOrderExist(orderId)) {
            throw new OrderNotFoundException();
        }
        hubsRepository.reduceResourceQuantityByOrder(orderId);
        ordersRepository.confirmOrder(orderId);
    }

    @Override
    public List<OrderInfoDto> getOrders(int page, int size, String sort, SortDirection direction) {
        return ordersRepository.getOrders(page,size, sort,direction);
    }

    @Override
    public List<HubOrderDTO> getHubOrders(int page, int itemPerPage) {
        return ordersRepository.getHubOrders(page, itemPerPage);
    }

    @Override
    public List<HubOrderDTO> getConfirmedOrders(int page, int itemPerPage) {
        return ordersRepository.getConfirmedOrders(page, itemPerPage);
    }
}