package org.vdoloka.repository;

import org.vdoloka.dto.HubResourcesDTO;

import java.util.List;

public interface HubsRepository {

    void reduceResourceQuantityByOrder(int orderId);

    void increaseResourceQuantityBySupplement(HubResourcesDTO hubResourcesDTO);

    List<HubResourcesDTO> getAllResources(int page, int itemPerPage);

    List<HubResourcesDTO> getResources(int page, int itemPerPage);
    List<HubResourcesDTO> getLackResources(int page, int itemPerPage);

    List<HubResourcesDTO> getCountOrderingResources(int page, int itemPerPage);
}