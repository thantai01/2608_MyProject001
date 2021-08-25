package com.personal.management.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter @Getter
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String createdTime;
    private long expenseAmount;
    private String description;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Funds fund;

    public Expense() {
    }

    public Expense(long expenseAmount, String description, Category category) {
        this.expenseAmount = expenseAmount;
        this.description = description;
        this.category = category;
    }
}
