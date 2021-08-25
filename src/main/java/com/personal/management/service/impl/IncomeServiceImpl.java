package com.personal.management.service.impl;

import com.personal.management.model.Income;
import com.personal.management.repository.IncomeRepository;
import com.personal.management.service.iservice.IIncome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IncomeServiceImpl implements IIncome {
    @Autowired
    private IncomeRepository incomeRepository;

    @Override
    public Iterable<Income> findAll() {
        return incomeRepository.findAll();
    }

    @Override
    public Optional<Income> findById(long id) {
        return incomeRepository.findById(id);
    }

    @Override
    public Income save(Income income) {
        return incomeRepository.save(income);
    }

    @Override
    public void delete(long id) {
        incomeRepository.deleteById(id);
    }

    @Override
    public Page<Income> findAllByPaging(Pageable pageable) {
        return incomeRepository.findAll(pageable);
    }

    @Override
    public Iterable<Income> findAllByCategoryId(long category_id) {
        return incomeRepository.findAllByCategoryId(category_id);
    }

    @Override
    public Iterable<Income> findAllByIncomeAmountGreaterThanEqual(long incomeAmount) {
        return incomeRepository.findAllByIncomeAmountGreaterThanEqual(incomeAmount);
    }
}
