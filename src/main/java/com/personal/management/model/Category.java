package com.personal.management.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String typeName;


    public Category() {
    }

    public Category(String name, String typeName) {
        this.name = name;
        this.typeName = typeName;
    }
}
