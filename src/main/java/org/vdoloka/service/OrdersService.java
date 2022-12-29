package org.vdoloka.service;

import org.vdoloka.dto.HubOrderDTO;
import org.vdoloka.dto.OrderDto;

import java.util.List;

public interface OrdersService {

    void addOrder(OrderDto orderDto);

    void confirmOrder(int orderId);

    List<OrderDto> getOrders(int page, int itemPerPage);

    List<HubOrderDTO> getHubOrders(int page, int itemPerPage);

    List<HubOrderDTO> getConfirmedOrders(int page, int itemPerPage);
}