package org.vdoloka.service;

import org.vdoloka.DTO.HubOrderDTO;
import org.vdoloka.entity.OrderEntity;

import java.util.List;

public interface OrdersService {

    void addOrder(OrderEntity orderEntity);

    void confirmOrder(int orderId);

    List<OrderEntity> getOrders(int Page, int itemPerPage);

    List<HubOrderDTO> getHubOrders(int Page, int itemPerPage);

    List<HubOrderDTO> getConfirmedOrders(int page, int itemPerPage);
}