package com.personal.management.repository;

import com.personal.management.model.Funds;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundRepository extends CrudRepository<Funds,Long> {
}
