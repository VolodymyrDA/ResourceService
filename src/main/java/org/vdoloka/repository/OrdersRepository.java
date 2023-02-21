package org.vdoloka.repository;

import org.vdoloka.dto.HubOrderDTO;
import org.vdoloka.dto.HubResourcesDTO;
import org.vdoloka.dto.OrderDto;
import org.vdoloka.dto.OrderInfoDto;
import org.vdoloka.model.SortDirection;

import java.util.List;

public interface OrdersRepository{

    void addOrder(OrderDto orderDto);

    void confirmOrder(int orderId);

    List<OrderInfoDto> getOrders(int page, int size, String sort, SortDirection direction);

    List<HubOrderDTO> getHubOrders(int page, int itemPerPage);

    List<HubOrderDTO> getConfirmedOrders(int page, int itemPerPage);

    List<HubResourcesDTO> getLackResources(int page, int itemPerPage);

    List<HubResourcesDTO> getCountOrderingResources(int page, int itemPerPage);

    boolean isOrderExist(int orderId);
}