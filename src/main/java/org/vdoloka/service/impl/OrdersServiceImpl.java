package org.vdoloka.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vdoloka.dto.HubOrderDTO;
import org.vdoloka.dto.OrderDto;
import org.vdoloka.dto.OrderInfoDto;
import org.vdoloka.exception.OrderNotFoundException;
import org.vdoloka.model.SortDirection;
import org.vdoloka.repository.HubsRepository;
import org.vdoloka.repository.OrdersRepository;
import org.vdoloka.service.OrdersService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {
    private final OrdersRepository ordersRepository;
    private final HubsRepository hubsRepository;

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
        return ordersRepository.getOrders(page, size, sort, direction);
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