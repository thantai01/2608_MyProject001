package com.personal.management.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String createdTime;
    private long incomeAmount;
    private String description;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Funds fund;

    public Income() {
    }

    public Income(long incomeAmount, String description, Category category) {
        this.incomeAmount = incomeAmount;
        this.description = description;
        this.category = category;
    }
}
