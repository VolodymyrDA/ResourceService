package org.vdoloka.entity;

public class LocationEntity {
    private final int id;
    private final String name;

    public LocationEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}