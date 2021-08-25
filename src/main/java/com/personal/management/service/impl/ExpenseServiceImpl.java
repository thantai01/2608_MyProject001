package com.personal.management.service.impl;
import com.personal.management.model.Expense;
import com.personal.management.model.Income;
import com.personal.management.repository.ExpenseRepository;
import com.personal.management.service.iservice.IExpense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExpenseServiceImpl implements IExpense {
    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public Iterable<Expense> findAll() {
        return expenseRepository.findAll();
    }

    @Override
    public Optional<Expense> findById(long id) {
        return expenseRepository.findById(id);
    }

    @Override
    public Expense save(Expense expense) {
        return expenseRepository.save(expense);
    }

    @Override
    public void delete(long id) {
        expenseRepository.deleteById(id);
    }

    @Override
    public Page<Expense> findAllByPaging(Pageable pageable) {
        return expenseRepository.findAll(pageable);
    }

    @Override
    public Iterable<Expense> findAllByCategoryId(long category_id) {
        return expenseRepository.findAllByCategoryId(category_id);
    }

    @Override
    public Iterable<Expense> findAllByIncomeAmountGreaterThanEqual(long expenseAmount) {
        return expenseRepository.findAllByIncomeAmountGreaterThanEqual(expenseAmount);
    }
}
