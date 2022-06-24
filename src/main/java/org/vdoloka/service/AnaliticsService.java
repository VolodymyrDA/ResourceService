package org.vdoloka.service;

import org.vdoloka.entity.HubEntity;

import java.util.List;

public interface AnaliticsService {
    List<HubEntity> getResourcesOnHubs(int Page, int itemPerPage);

    List<HubEntity> getLackResources(int page, int itemPerPage);

    List<HubEntity> getCountOrderingResources(int page, int itemPerPage);
}