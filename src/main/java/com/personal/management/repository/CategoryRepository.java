package com.personal.management.repository;

import com.personal.management.model.Category;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Long> {
}
