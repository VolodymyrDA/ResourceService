package org.vdoloka.service.impl;

import org.vdoloka.dto.HubResourcesDTO;
import org.vdoloka.repository.impl.HubsRepositoryImpl;
import org.vdoloka.repository.impl.OrdersRepositoryImpl;
import org.vdoloka.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyticsServiceImpl implements AnalyticsService {

    private final OrdersRepositoryImpl ordersRepository;
    private final HubsRepositoryImpl hubsRepository;

    @Autowired
    public AnalyticsServiceImpl(OrdersRepositoryImpl ordersRepository, HubsRepositoryImpl hubsRepository) {
        this.ordersRepository = ordersRepository;
        this.hubsRepository = hubsRepository;
    }

    @Override
    public List<HubResourcesDTO> getResourcesOnHubs(int page, int itemPerPage) {

        return hubsRepository.getAllResources(page, itemPerPage);
    }

    @Override
    public List<HubResourcesDTO> getLackResources(int page, int itemPerPage) {
        return ordersRepository.getLackResources(page, itemPerPage);

    }

    @Override
    public List<HubResourcesDTO> getCountOrderingResources(int page, int itemPerPage) {

        return ordersRepository.getCountOrderingResources(page, itemPerPage);
    }
}