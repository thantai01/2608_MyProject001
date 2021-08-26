package com.personal.management.controller;

import com.personal.management.model.Expense;
import com.personal.management.service.iservice.IExpense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ExpenseAPIController {
    @Autowired
    private IExpense expenseService;

    @GetMapping("/expenses/paging")
    ResponseEntity<Iterable<Expense>> findAllByPage(Pageable pageable) {
        return new ResponseEntity<>(expenseService.findAllByPaging(pageable), HttpStatus.OK);
    }

    @PostMapping("/expenses")
    ResponseEntity<Expense> save(@RequestBody Expense expense) {
        expense.setCreatedTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        expenseService.save(expense);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("/expenses/{id}")
    ResponseEntity<Optional<Expense>> edit (@PathVariable long id, Expense expense) {
        Optional<Expense> selected = expenseService.findById(id);
        if(selected.isPresent()) {
            expense.setId(id);
            expense.setCreatedTime(selected.get().getCreatedTime());
            expenseService.save(expense);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/expenses/findAllByCategory/{categoryId}")
    ResponseEntity<Iterable<Expense>> findAllByCategory(@PathVariable long categoryId) {
        Iterable<Expense> foundList = expenseService.findAllByCategoryId(categoryId);
        return new ResponseEntity<>(foundList,HttpStatus.FOUND);
    }
    @GetMapping("/expenses/findAllByExpenseAmount/{expenseAmount}")
        ResponseEntity<Iterable<Expense>> findAllByExpenseAmountContaining (@PathVariable long expenseAmount) {
            Iterable<Expense> foundList = expenseService.findAllByExpenseAmountGreaterThanEqual(expenseAmount);
            return new ResponseEntity<>(foundList,HttpStatus.FOUND);
        }
    @GetMapping("/expenses")
    ResponseEntity<Iterable<Expense>> findAll() {
        return new ResponseEntity<>(expenseService.findAll(), HttpStatus.OK);
    }
}
