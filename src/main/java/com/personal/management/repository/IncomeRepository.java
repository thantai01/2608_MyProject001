package com.personal.management.repository;

import com.personal.management.model.Income;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepository extends PagingAndSortingRepository<Income,Long> {
    Iterable<Income> findAllByCategoryId(long category_id);

    @Query("select income from Income income where income.incomeAmount >= ?1 ")
    Iterable<Income> findAllByIncomeAmountGreaterThanEqual(long incomeAmount);
}
