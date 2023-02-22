package org.vdoloka.model;

public enum AnalyticsType {
    LACK_RESOURCES("lackResources", "Lack of resources"),
    TOP_ORDERING_RESOURCES("topOrderingResources", "Top ordering resources"),
    RESOURCES_ON_HUBS("resourcesOnHubs", "Resources on hubs");

    private final String fileName;
    private final String description;

    AnalyticsType(String fileName, String description) {
        this.fileName = fileName;
        this.description = description;
    }

    public String getFileName() {
        return fileName;
    }

    public String getDescription() {
        return description;
    }
}