package org.vdoloka.service;

import org.vdoloka.model.AnalyticsType;
import org.vdoloka.dto.HubResourcesDTO;

import java.util.List;

public interface ResourcesService {
    List<HubResourcesDTO> getAnalytics(AnalyticsType analyticsType, int page, int itemsPerPage);

    List<HubResourcesDTO> getResources(int page, int itemPerPage);

    void increaseResourceQuantityBySupplement(HubResourcesDTO hubResourcesDTO);
}