package org.vdoloka.entity;

public class CategoryEntity {
    private final int id;
    private final String name;

    public CategoryEntity(int id, String name) {
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