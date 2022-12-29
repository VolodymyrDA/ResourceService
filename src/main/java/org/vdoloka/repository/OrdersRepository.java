package org.vdoloka.repository;

import org.vdoloka.dto.HubOrderDTO;
import org.vdoloka.dto.HubResourcesDTO;
import org.vdoloka.dto.OrderDto;

import java.util.List;

public interface OrdersRepository {

    void addOrder(OrderDto orderDto);

    void confirmOrder(int orderId);

    List<OrderDto> getOrders(int page, int itemPerPage);

    List<HubOrderDTO> getHubOrders(int page, int itemPerPage);

    List<HubOrderDTO> getConfirmedOrders(int page, int itemPerPage);

    List<HubResourcesDTO> getLackResources(int page, int itemPerPage);

    List<HubResourcesDTO> getCountOrderingResources(int page, int itemPerPage);
}