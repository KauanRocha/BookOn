package br.com.bookon.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.bookon.server.models.mongo.Loan;
import br.com.bookon.server.payload.request.mongo.LoanRequest;
import br.com.bookon.server.services.LoanService;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping
    public ResponseEntity<List<Loan>> getAllLoans() {
        List<Loan> loans = loanService.getAllLoans();
        return new ResponseEntity<>(loans, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoanById(@PathVariable String id) {
        Loan loan = loanService.getLoanById(id);
        if (loan != null) {
            return new ResponseEntity<>(loan, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Loan> createLoan(@RequestBody LoanRequest loanRequest) {
        Loan createdLoan = loanService.createLoan(loanRequest);
        return new ResponseEntity<>(createdLoan, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable String id) {
        boolean deleted = loanService.deleteLoan(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
