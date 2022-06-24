package org.vdoloka.repository;

import org.vdoloka.entity.LocationEntity;

import java.util.List;

public interface LocationsRepository {

    List<LocationEntity> getLocations();

}