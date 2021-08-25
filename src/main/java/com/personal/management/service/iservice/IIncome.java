package com.personal.management.service.iservice;

import com.personal.management.model.Income;
import com.personal.management.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IIncome extends IGeneralService<Income> {
    Page<Income> findAllByPaging(Pageable pageable);
    Iterable<Income> findAllByCategoryId(long category_id);
    Iterable<Income> findAllByIncomeAmountGreaterThanEqual(long incomeAmount);
}
