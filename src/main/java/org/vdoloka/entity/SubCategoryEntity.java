package org.vdoloka.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "SUBCATEGORIES")
public class SubCategoryEntity {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "subcategory_id")
    private int id;
    private String name;

    public SubCategoryEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public SubCategoryEntity() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}