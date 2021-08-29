package com.personal.management.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 48)
    private String name;


    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }
}
