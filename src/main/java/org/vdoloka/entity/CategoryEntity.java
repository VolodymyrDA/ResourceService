package org.vdoloka.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "CATEGORIES")
public class CategoryEntity {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "categorie_id")
    private int id;
    private String name;

    public CategoryEntity() {
    }

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