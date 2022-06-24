package org.vdoloka.entity;

public class ResourceEntity {
    private final int id;
    private final String name;

    public ResourceEntity(int id, String name) {
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
