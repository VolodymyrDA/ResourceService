package org.vdoloka.service.impl;

import org.vdoloka.entity.HubEntity;
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
    public List<HubEntity> getResourcesOnHubs(int page, int itemPerPage) {
        List<HubEntity> resourcesOnHubs = hubsRepository.getAllResources(page, itemPerPage);
        return resourcesOnHubs;
    }

    @Override
    public List<HubEntity> getLackResources(int page, int itemPerPage) {
        List<HubEntity> lackResources = ordersRepository.getLackResources(page, itemPerPage);
        return lackResources;
    }

    @Override
    public List<HubEntity> getCountOrderingResources(int page, int itemPerPage) {
        List<HubEntity> lackResources = ordersRepository.getCountOrderingResources(page, itemPerPage);
        return lackResources;
    }
}