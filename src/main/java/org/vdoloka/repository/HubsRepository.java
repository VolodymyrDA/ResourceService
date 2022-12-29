package org.vdoloka.repository;

import org.vdoloka.dto.HubResourcesDTO;

import java.util.List;

public interface HubsRepository {

    void reduceResourceQuantityByOrder(int orderId);

    List<HubResourcesDTO> getAllResources(int page, int itemPerPage);

    List<HubResourcesDTO> getResources(int page, int itemPerPage);
}