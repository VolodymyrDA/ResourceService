package org.vdoloka.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vdoloka.dto.HubResourcesDTO;
import org.vdoloka.model.AnalyticsType;
import org.vdoloka.repository.HubsRepository;
import org.vdoloka.service.ResourcesService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResourcesServiceImpl implements ResourcesService {
    private final HubsRepository hubsRepository;

    @Override
    public List<HubResourcesDTO> getAnalytics(AnalyticsType analyticsType, int page, int itemsPerPage) {

        return switch (analyticsType) {
            case RESOURCES_ON_HUBS -> hubsRepository.getAllResources(page, itemsPerPage);
            case LACK_RESOURCES -> hubsRepository.getLackResources(page, itemsPerPage);
            case TOP_ORDERING_RESOURCES -> hubsRepository.getCountOrderingResources(page, itemsPerPage);
            default ->
                    throw new UnsupportedOperationException("Export type " + analyticsType.name() + " is not supported");
        };
    }

    @Override
    public List<HubResourcesDTO> getResources(int page, int itemPerPage) {
        return hubsRepository.getResources(page, itemPerPage);
    }

    @Override
    public void increaseResourceQuantityBySupplement(HubResourcesDTO hubResourcesDTO) {
        hubsRepository.increaseResourceQuantityBySupplement(hubResourcesDTO);
    }
}