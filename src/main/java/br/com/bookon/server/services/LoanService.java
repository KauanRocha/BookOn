package br.com.bookon.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bookon.server.enumerations.LoanStatusEnum;
import br.com.bookon.server.models.mongo.Loan;
import br.com.bookon.server.models.postgres.Book;
import br.com.bookon.server.models.postgres.User;
import br.com.bookon.server.payload.request.mongo.LoanRequest;
import br.com.bookon.server.payload.response.mongo.LoanResponse;
import br.com.bookon.server.repository.mongo.LoanRepository;
import br.com.bookon.server.repository.postgres.BookRepository;
import br.com.bookon.server.repository.postgres.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BookRepository bookRepository;
    
    public List<LoanResponse> getAllLoans() {
        List<Loan> loans = loanRepository.findAll();
        List<LoanResponse> loanResponses = new ArrayList<>();

        for (Loan loan : loans) {
            var loanResponse = new LoanResponse(loan);
            loanResponses.add(loanResponse);
        }

        return loanResponses;
    }

    public LoanResponse getLoanById(String id) {
        Loan loan = loanRepository.findById(id).orElse(null);
        return new LoanResponse(loan);
    }

    public LoanResponse createLoan(LoanRequest loanRequest) {
    	Loan loan = new Loan();
        User borrowerPostgres = userRepository.findById(loanRequest.getBorrowerId()).orElseThrow(null);
        br.com.bookon.server.models.mongo.User borrower = new br.com.bookon.server.models.mongo.User(borrowerPostgres);
        loan.setBorrowerUser(borrower);
        
        User lenderPostgres = userRepository.findById(loanRequest.getLenderId()).orElseThrow(null);
        br.com.bookon.server.models.mongo.User lender = new br.com.bookon.server.models.mongo.User(lenderPostgres);
        loan.setLenderUser(lender);
        
        Book bookPostgres = bookRepository.findById(loanRequest.getBookId()).orElseThrow(null);
        br.com.bookon.server.models.mongo.Book book = new br.com.bookon.server.models.mongo.Book(bookPostgres);
        loan.setBook(book);
        loan.setReturnDate(null);
        
        return new LoanResponse(loanRepository.save(loan));
    }

    public boolean deleteLoan(String id) {
        if (loanRepository.existsById(id)) {
            loanRepository.deleteById(id);
            return true; 
        } else {
            return false;
        }
    }
    
    public LoanResponse createPropose(LoanRequest loanRequest, Integer borrowerId) {
    	Loan loan = new Loan();
        User borrowerPostgres = userRepository.findById(borrowerId).orElseThrow(null);
        br.com.bookon.server.models.mongo.User borrower = new br.com.bookon.server.models.mongo.User(borrowerPostgres);
        loan.setBorrowerUser(borrower);
        
        User lenderPostgres = userRepository.findById(loanRequest.getLenderId()).orElseThrow(null);
        br.com.bookon.server.models.mongo.User lender = new br.com.bookon.server.models.mongo.User(lenderPostgres);
        loan.setLenderUser(lender);
        
        Book bookPostgres = bookRepository.findById(loanRequest.getBookId()).orElseThrow(null);
        br.com.bookon.server.models.mongo.Book book = new br.com.bookon.server.models.mongo.Book(bookPostgres);
        loan.setBook(book);
        loan.setReturnDate(null);
        loan.setStatus(LoanStatusEnum.PENDING);
        
        return new LoanResponse(loanRepository.save(loan));
    }
    
    public List<LoanResponse> listPropose(Integer lenderId) {
        List<Loan> loans = loanRepository.findByLenderUserIdAndStatus(lenderId, LoanStatusEnum.PENDING);
        List<LoanResponse> loanResponses = new ArrayList<>();

        for (Loan loan : loans) {
            var loanResponse = new LoanResponse(loan);
            loanResponses.add(loanResponse);
        }

        return loanResponses;
    }
    
    public void approvePropose(String loanId) {
        Loan loan = loanRepository.findById(loanId).orElse(null);
        loan.setStatus(LoanStatusEnum.APPROVED);
        loanRepository.save(loan);
        
    }
    
}
