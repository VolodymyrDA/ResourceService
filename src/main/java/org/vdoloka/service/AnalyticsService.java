package org.vdoloka.service;

import org.vdoloka.model.AnalyticsType;
import org.vdoloka.dto.HubResourcesDTO;

import java.util.List;

public interface AnalyticsService {
    List<HubResourcesDTO> getData(AnalyticsType analyticsType, int page, int itemsPerPage);
}