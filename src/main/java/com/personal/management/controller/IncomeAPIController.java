package com.personal.management.controller;
import com.personal.management.model.Income;
import com.personal.management.service.iservice.IIncome;
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
public class IncomeAPIController {
    @Autowired
    private IIncome incomeService;

    @GetMapping("/incomes")
    ResponseEntity<Iterable<Income>> findAllByPaging(Pageable pageable) {
        return new ResponseEntity<>(incomeService.findAllByPaging(pageable), HttpStatus.FOUND);
    }
    @PostMapping("/incomes")
    ResponseEntity<Income> save(@RequestBody Income income) {
        income.setCreatedTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        incomeService.save(income);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("/incomes/{id}")
    ResponseEntity<Optional<Income>> edit(@PathVariable long id,@RequestBody Income income) {
        Optional<Income> selected = incomeService.findById(id);
        if(selected.isPresent()) {
            income.setId(id);
            income.setCreatedTime(selected.get().getCreatedTime());
            incomeService.save(income);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/incomes/{id}")
    ResponseEntity<Optional<Income>> delete(@PathVariable long id) {
        Optional<Income> selected = incomeService.findById(id);
        if(selected.isPresent()) {
            incomeService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/incomes/{id}/detail")
    ResponseEntity<Optional<Income>> detail(@PathVariable long id) {
        Optional<Income> selected = incomeService.findById(id);
        if(!selected.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(selected,HttpStatus.FOUND);
    }
    @GetMapping("/incomes/searchByCategory")
    ResponseEntity<Iterable<Income>> findAllByCategory(@RequestParam long categoryId) {
        Iterable<Income> foundList = incomeService.findAllByCategoryId(categoryId);
        return new ResponseEntity<>(foundList,HttpStatus.OK);
    }
    @GetMapping("/incomes/searchByAmount")
    ResponseEntity<Iterable<Income>> findAllByIncomeAmount(@RequestParam long incomeAmount) {
        Iterable<Income> foundList = incomeService.findAllByIncomeAmountGreaterThanEqual(incomeAmount);
        return new ResponseEntity<>(foundList,HttpStatus.OK);
    }

}
