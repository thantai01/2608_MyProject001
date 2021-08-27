package com.personal.management.controller;

import com.personal.management.model.Funds;
import com.personal.management.service.iservice.IFund;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class FundAPIController {
    @Autowired
    private IFund fundService;

    @GetMapping("/funds")
    ResponseEntity<Iterable<Funds>> findAll() {
        return new ResponseEntity<>(fundService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/funds")
    ResponseEntity<Funds> create() {
        fundService.save(new Funds());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/funds/{id}/detail")
    ResponseEntity<Optional<Funds>> detail(@PathVariable long id) {
        Optional<Funds> selected = fundService.findById(id);
        if(selected.isPresent()) {
            return new ResponseEntity<>(selected,HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/funds/{id}")
    ResponseEntity<Void> delete(@PathVariable long id) {
        Optional<Funds> selected = fundService.findById(id);
        if(selected.isPresent()) {
            fundService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/funds/{id}/update-balance")
    ResponseEntity<Optional<Funds>> updateFundBalance(@PathVariable long id,@RequestBody Funds fund) {
        Optional<Funds> selected = fundService.findById(id);
        if(selected.isPresent()) {
            fund.setId(id);
            fund.setBalance(selected.get().getTotalIncome()-selected.get().getTotalExpense());
            fundService.save(fund);
            return new ResponseEntity<>(selected,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PutMapping("/funds/{id}/update-totalIncome")
    ResponseEntity<Optional<Funds>> updateTotalIncome(@PathVariable long id,@RequestParam long totalIncome,@RequestBody Funds fund) {
        Optional<Funds> selected = fundService.findById(id);
        if(selected.isPresent()) {
            fund.setId(selected.get().getId());
            fund.setTotalIncome(selected.get().getTotalIncome()+totalIncome);
            fundService.save(fund);
            return new ResponseEntity<>(selected,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PutMapping("/funds/{id}/update-totalExpense")
    ResponseEntity<Optional<Funds>> updateTotalExpense(@PathVariable long id, @RequestParam long totalExpense,@RequestBody Funds fund) {
        Optional<Funds> selected = fundService.findById(id);
        if(selected.isPresent()) {
            fund.setId(id);
            fund.setTotalExpense(selected.get().getTotalExpense()+totalExpense);
            fundService.save(fund);
            return  new ResponseEntity<>(selected,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PutMapping("funds/{id}/set-limiter")
    ResponseEntity<Optional<Funds>> setLimiter (@PathVariable long id, @RequestParam long limit, @RequestBody Funds fund) {
        Optional<Funds> selected = fundService.findById(id);
        if(selected.isPresent()) {
            fund.setId(id);
            fund.setLimited(limit);
            fundService.save(fund);
            return new ResponseEntity<>(selected,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
