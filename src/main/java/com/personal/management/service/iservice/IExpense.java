package com.personal.management.service.iservice;

import com.personal.management.model.Expense;
import com.personal.management.model.Income;
import com.personal.management.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IExpense extends IGeneralService<Expense> {
    Page<Expense> findAllByPaging(Pageable pageable);

    Iterable<Expense> findAllByCategoryId(long category_id);
    Iterable<Expense> findAllByExpenseAmountGreaterThanEqual(long expenseAmount);
}
