package com.personal.management.service;

import java.util.Optional;

public interface IGeneralService <T>{
    Iterable<T> findAll();
    Optional<T> findById(long id);
    T save(T t);
    void delete(long id);
}
