package com.personal.management.controller;

import com.personal.management.model.Expense;
import com.personal.management.service.iservice.IExpense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ExpenseAPIController {
    @Autowired
    private IExpense expenseService;

    @GetMapping("/expenses")
    ResponseEntity<Iterable<Expense>> findAll() {
        return new ResponseEntity<>(expenseService.findAll(), HttpStatus.OK);
    }

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

    @GetMapping("/expense/{id}")
    ResponseEntity<Optional<Expense>> detail (@PathVariable long id) {
        Optional<Expense> selected = expenseService.findById(id);
        if(selected.isPresent()) {
            return new ResponseEntity<>(HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
    @DeleteMapping("/expenses/{id}")
    ResponseEntity<Void> delete(@PathVariable long id) {
        Optional<Expense> selected = expenseService.findById(id);
        if(selected.isPresent()) {
            expenseService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/expenses/searchByCategory")
    ResponseEntity<Iterable<Expense>> findAllByCategory(@RequestParam long categoryId) {
        Iterable<Expense> foundList = expenseService.findAllByCategoryId(categoryId);
        return new ResponseEntity<>(foundList,HttpStatus.FOUND);
    }
    @GetMapping("/expenses/searchByAmount")
        ResponseEntity<Iterable<Expense>> findAllByExpenseAmountContaining (@RequestParam long expenseAmount) {
            Iterable<Expense> foundList = expenseService.findAllByExpenseAmountGreaterThanEqual(expenseAmount);
            return new ResponseEntity<>(foundList,HttpStatus.FOUND);
        }

    @GetMapping("/expenses/top5Expenses")
    ResponseEntity<Iterable<Expense>> findTop5Expense() {
        return new ResponseEntity<>(expenseService.findTop5Expense(),HttpStatus.FOUND);
    }
    @GetMapping("/expenses/search")
    ResponseEntity<Iterable<Expense>> findALlByLocalDate(@RequestParam LocalDate start, @RequestParam LocalDate end) {
        Iterable<Expense> foundList;
        for(Expense expense: expenseService.findAll()) {
            LocalDate expenseTime = LocalDate.parse(expense.getCreatedTime(),DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            if(expenseTime.compareTo(start)>=0 && expenseTime.compareTo(end)>=0) {
                foundList = expenseService.findAllByCreatedTimeEquals(expenseTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
                return new ResponseEntity<>(foundList,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
