package org.vdoloka.service.impl;

import org.vdoloka.dto.HubResourcesDTO;
import org.vdoloka.repository.impl.HubsRepositoryImpl;
import org.vdoloka.repository.impl.OrdersRepositoryImpl;
import org.vdoloka.service.AnaliticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyticsServiceImpl implements AnaliticsService {

    private final OrdersRepositoryImpl ordersRepository;
    private final HubsRepositoryImpl hubsRepository;

    @Autowired
    public AnalyticsServiceImpl(OrdersRepositoryImpl ordersRepository, HubsRepositoryImpl hubsRepository) {
        this.ordersRepository = ordersRepository;
        this.hubsRepository = hubsRepository;
    }

    @Override
    public List<HubResourcesDTO> getResourcesOnHubs(int page, int itemPerPage) {
        List<HubResourcesDTO> resourcesOnHubResourcesDTOS = hubsRepository.getAllResources(page, itemPerPage);
        return resourcesOnHubResourcesDTOS;
    }

    @Override
    public List<HubResourcesDTO> getLackResources(int page, int itemPerPage) {
        List<HubResourcesDTO> lackResources = ordersRepository.getLackResources(page, itemPerPage);
        return lackResources;
    }

    @Override
    public List<HubResourcesDTO> getCountOrderingResources(int page, int itemPerPage) {
        List<HubResourcesDTO> lackResources = ordersRepository.getCountOrderingResources(page, itemPerPage);
        return lackResources;
    }
}