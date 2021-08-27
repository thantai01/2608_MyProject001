package com.personal.management.repository;

import com.personal.management.model.Expense;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends PagingAndSortingRepository<Expense,Long> {
    Iterable<Expense> findAllByCategoryId(long category_id);

    @Query("select expense from Expense expense where expense.expenseAmount >= ?1 ")
    Iterable<Expense> findAllByIncomeAmountGreaterThanEqual(long incomeAmount);

    @Query(value = "select * from expense order by expense_amount desc limit 5 ",nativeQuery = true)
    Iterable<Expense> findTop5Expense();

    @Query(value = "select * from expense where created_time =?1 order by expense_amount desc" ,nativeQuery = true)
    Iterable<Expense> findAllByCreatedTimeEquals(String expenseTime);
}
