package com.personal.management.controller;

import com.personal.management.model.Category;
import com.personal.management.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class CategoryAPIController {
    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/categories")
    ResponseEntity<Iterable<Category>> findAll() {
        return new ResponseEntity<>(categoryRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/categories")
    ResponseEntity<Category> save(@RequestBody Category category) {
        categoryRepository.save(category);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/categories/{id}")
    ResponseEntity<Void> delete(@PathVariable long id) {
        categoryRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/categories/{id}")
    ResponseEntity<Optional<Category>> edit (@PathVariable long id, Category category) {
        Optional<Category> selected = categoryRepository.findById(id);
        if(selected.isPresent()) {
            category.setId(id);
            categoryRepository.save(category);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/categories/filter")
    ResponseEntity<Iterable<Category>> filterList (@RequestParam String typeName) {
        Iterable<Category> iterable = categoryRepository.findNameByNameType(typeName);
        return new ResponseEntity<>(iterable,HttpStatus.OK);
    }
}
