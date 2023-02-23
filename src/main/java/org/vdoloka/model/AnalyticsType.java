package org.vdoloka.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum AnalyticsType {
    LACK_RESOURCES("lackResources", "Lack of resources"),
    TOP_ORDERING_RESOURCES("topOrderingResources", "Top ordering resources"),
    RESOURCES_ON_HUBS("resourcesOnHubs", "Resources on hubs");
    @Getter
    private final String fileName;
    @Getter
    private final String description;
}