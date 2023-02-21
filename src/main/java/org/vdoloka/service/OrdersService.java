package org.vdoloka.service;

import org.vdoloka.dto.HubOrderDTO;
import org.vdoloka.dto.OrderDto;
import org.vdoloka.dto.OrderInfoDto;
import org.vdoloka.model.SortDirection;

import java.util.List;

public interface OrdersService {

    void addOrder(OrderDto orderDto);

    void confirmOrder(int orderId);

    List<OrderInfoDto> getOrders(int page, int size, String sort, SortDirection direction);

    List<HubOrderDTO> getHubOrders(int page, int itemPerPage);

    List<HubOrderDTO> getConfirmedOrders(int page, int itemPerPage);
}