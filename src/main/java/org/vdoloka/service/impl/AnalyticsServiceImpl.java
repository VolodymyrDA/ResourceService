package org.vdoloka.service.impl;

import lombok.RequiredArgsConstructor;
import org.vdoloka.dto.HubResourcesDTO;
import org.vdoloka.model.AnalyticsType;
import org.vdoloka.repository.OrdersRepository;
import org.vdoloka.repository.impl.HubsRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalyticsServiceImpl implements org.vdoloka.service.AnalyticsService {

    private final OrdersRepository ordersRepository;
    private final HubsRepositoryImpl hubsRepository;

    @Override
    public List<HubResourcesDTO> getData(AnalyticsType analyticsType, int page, int itemsPerPage) {

        return switch (analyticsType) {
            case RESOURCES_ON_HUBS -> hubsRepository.getAllResources(page, itemsPerPage);
            case LACK_RESOURCES -> ordersRepository.getLackResources(page, itemsPerPage);
            case TOP_ORDERING_RESOURCES -> ordersRepository.getCountOrderingResources(page, itemsPerPage);
            default ->
                    throw new UnsupportedOperationException("Export type " + analyticsType.name() + " is not supported");
        };
    }
}