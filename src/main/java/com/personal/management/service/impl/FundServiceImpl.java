package com.personal.management.service.impl;

import com.personal.management.model.Funds;
import com.personal.management.repository.FundRepository;
import com.personal.management.service.iservice.IFund;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FundServiceImpl implements IFund {
    @Autowired
    private FundRepository fundRepository;

    @Override
    public Iterable<Funds> findAll() {
        return fundRepository.findAll();
    }

    @Override
    public Optional<Funds> findById(long id) {
        return fundRepository.findById(id);
    }

    @Override
    public Funds save(Funds funds) {
        return fundRepository.save(funds);
    }

    @Override
    public void delete(long id) {
        fundRepository.deleteById(id);
    }
}
