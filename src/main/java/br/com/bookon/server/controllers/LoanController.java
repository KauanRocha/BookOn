package br.com.bookon.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.bookon.server.payload.request.mongo.LoanRequest;
import br.com.bookon.server.payload.response.mongo.LoanResponse;
import br.com.bookon.server.services.LoanService;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping
    public ResponseEntity<List<LoanResponse>> getAllLoans() {
        List<LoanResponse> loans = loanService.getAllLoans();
        return new ResponseEntity<>(loans, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanResponse> getLoanById(@PathVariable String id) {
    	var loan = loanService.getLoanById(id);
        if (loan != null) {
            return new ResponseEntity<>(loan, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<LoanResponse> createLoan(@RequestBody LoanRequest loanRequest) {
    	var createdLoan = loanService.createLoan(loanRequest);
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
    
    @PostMapping("/{borrowerId}/create")
    public ResponseEntity<LoanResponse> createPropose(@RequestBody LoanRequest loanRequest, @PathVariable Integer borrowerId) {
    	var createdLoan = loanService.createPropose(loanRequest, borrowerId);
        return new ResponseEntity<>(createdLoan, HttpStatus.CREATED);
    }
    
    @GetMapping("/{lenderId}/search")
    public ResponseEntity<List<LoanResponse>> listPropose(@PathVariable Integer lenderId) {
    	List<LoanResponse> loans = loanService.listPropose(lenderId);
        return new ResponseEntity<>(loans, HttpStatus.OK);
    }
    
    @PutMapping("/{loanId}/approve")
    public ResponseEntity<Void> approvePropose(@PathVariable String loanId) {
    	loanService.approvePropose(loanId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}