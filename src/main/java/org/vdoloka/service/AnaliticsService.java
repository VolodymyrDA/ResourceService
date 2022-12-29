package org.vdoloka.service;

import org.vdoloka.dto.HubResourcesDTO;

import java.util.List;

public interface AnaliticsService {
    List<HubResourcesDTO> getResourcesOnHubs(int Page, int itemPerPage);

    List<HubResourcesDTO> getLackResources(int page, int itemPerPage);

    List<HubResourcesDTO> getCountOrderingResources(int page, int itemPerPage);
}