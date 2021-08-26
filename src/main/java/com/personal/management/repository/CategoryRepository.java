package com.personal.management.repository;

import com.personal.management.model.Category;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category,Long> {

    @Modifying
    @Query("select category.name from Category category where category.typeName =?1 ")
    Iterable<Category> findNameByNameType(String name);
}
