package org.vdoloka.repository;

import org.vdoloka.entity.HubEntity;

import java.util.List;

public interface HubsRepository {

    void reduceResourceQuantityByOrder(int orderId);

    List<HubEntity> getAllResources(int page, int itemPerPage);

    List<HubEntity> getResources(int page, int itemPerPage);
}