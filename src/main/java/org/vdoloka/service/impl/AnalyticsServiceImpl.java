package org.vdoloka.service.impl;

import lombok.RequiredArgsConstructor;
import org.vdoloka.dto.HubResourcesDTO;
import org.vdoloka.model.AnalyticsType;
import org.vdoloka.repository.impl.HubsRepositoryImpl;
import org.vdoloka.repository.impl.OrdersRepositoryImpl;
import org.vdoloka.service.AnalyticsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalyticsServiceImpl implements AnalyticsService {

    private final OrdersRepositoryImpl ordersRepository;
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