package com.personal.management.repository;

import com.personal.management.model.Expense;
import com.personal.management.model.Income;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends PagingAndSortingRepository<Expense,Long> {
    Iterable<Expense> findAllByCategoryId(long category_id);

    @Query("select expense from Expense expense where expense.expenseAmount >= ?1 ")
    Iterable<Expense> findAllByIncomeAmountGreaterThanEqual(long incomeAmount);
}
