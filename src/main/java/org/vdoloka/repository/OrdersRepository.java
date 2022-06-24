package org.vdoloka.repository;

import org.vdoloka.DTO.HubOrderDTO;
import org.vdoloka.entity.HubEntity;
import org.vdoloka.entity.OrderEntity;

import java.util.List;

public interface OrdersRepository {

    void addOrder(OrderEntity orderEntity);

    void confirmOrder(int orderId);

    List<OrderEntity> getOrders(int Page, int itemPerPage);

    List<HubOrderDTO> getHubOrders(int Page, int itemPerPage);

    List<HubOrderDTO> getConfirmedOrders(int page, int itemPerPage);

    List<HubEntity> getLackResources(int page, int itemPerPage);

    List<HubEntity> getCountOrderingResources(int page, int itemPerPage);
}