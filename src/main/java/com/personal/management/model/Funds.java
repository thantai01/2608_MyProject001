package com.personal.management.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter @Getter
public class Funds {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long balance;
    private long totalIncome;
    private long totalExpense;
    private long limited;

    public Funds() {
        this.balance = 0;
        this.totalIncome = 0;
        this.totalExpense = 0;
        this.limited = 0;
    }
}
